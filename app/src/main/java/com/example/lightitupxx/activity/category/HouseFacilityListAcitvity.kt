package com.example.lightitupxx.activity.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lightitupxx.R
import com.example.lightitupxx.activity.detail.HouseDetailActivity
import com.example.lightitupxx.adapter.FieldAdapter
import com.example.lightitupxx.adapter.HomeAdapter
import com.example.lightitupxx.api.Facility_info
import com.example.lightitupxx.api.LocationItem
import com.example.lightitupxx.api.onItemClicked
import kotlinx.android.synthetic.main.activity_house_facility_list_acitvity.*
import java.util.ArrayList

class HouseFacilityListAcitvity : AppCompatActivity(), onItemClicked {
    lateinit var backButton: View
    lateinit var fieldAdapter: FieldAdapter
    lateinit var facilityAdapter_wal: HomeAdapter
    lateinit var facilityAdapter_jun : HomeAdapter
    lateinit var facilityAdapter_meme : HomeAdapter
    var field_idx:Int=0

    val field = ArrayList<LocationItem>()
    val house_wal = ArrayList<Facility_info>()
    val house_jun = ArrayList<Facility_info >()
    val house_meme= ArrayList<Facility_info >()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_facility_list_acitvity)

        backButton = findViewById(R.id.img_homePageBack)
        fieldAdapter = FieldAdapter(field)
        fieldlist_home.adapter = fieldAdapter
        fieldlist_home.setHasFixedSize(true) //어뎁터에 성능을 위한 것
        //레이아웃 매니저를 이용해 어뎁터의 방향을 결정
        fieldlist_home.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        facilityAdapter_wal = HomeAdapter (house_wal, this)
        facilityAdapter_jun= HomeAdapter (house_jun, this)
        facilityAdapter_meme = HomeAdapter (house_meme, this)

        recyclerGridView_home.adapter=facilityAdapter_wal
        recyclerGridView_home.layoutManager= GridLayoutManager(applicationContext,2)

        backButton.setOnClickListener {
            onBackPressed()
        }

        fieldAdapter.setItemClickListener(object : FieldAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                field_idx=position

                Toast.makeText(v.context,
                    field[position].location, Toast.LENGTH_SHORT).show()
                fieldCheck(v, position)
            }
        })
        setField(fieldAdapter)
        setHospitalsList()
    }

    //주거시설 리스트 아이템 클릭시 실행되는 함수
    override fun onGridItemClick(idx: Int) {
        Log.d("key", "gsg")
        val intentGoToHomeDetail = Intent(this, HouseDetailActivity::class.java)
        if(field_idx==0){
            intentGoToHomeDetail.putExtra("house",house_wal[idx])
        }else if(field_idx==1){
            intentGoToHomeDetail.putExtra("house",house_jun[idx])
        }else if(field_idx==2){
            intentGoToHomeDetail.putExtra("house",house_meme[idx])
        }else{
            Toast.makeText(this,"해당 필드가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
        }
        startActivity(intentGoToHomeDetail)
    }

    private fun fieldCheck(v: View, position: Int){
        if(field[position].location=="월세"){
            recyclerGridView_home.adapter=facilityAdapter_wal
            recyclerGridView_home.layoutManager= GridLayoutManager(applicationContext,2)
        }
        else if(field[position].location=="전세"){
            recyclerGridView_home.adapter=facilityAdapter_jun
            recyclerGridView_home.layoutManager= GridLayoutManager(applicationContext,2)
        }
        else if(field[position].location=="매매") {
            recyclerGridView_home.adapter=facilityAdapter_meme
            recyclerGridView_home.layoutManager= GridLayoutManager(applicationContext,2)
        }
    }

    private fun setField(fieldAdapter: FieldAdapter) {
        field.add(LocationItem("월세"))
        field.add(LocationItem("전세"))
        field.add(LocationItem("매매"))
    }

    private fun setHospitalsList(){
        house_wal.add(Facility_info (R.drawable.img_home_sanggyejugong7,"[상계주공7단지]","월세","701동 1211호","서울시 노원구", 37.65904745825612, 127.0604076840165,
        "02-933-3644","서울 노원구 동일로 1456","노원역9번 출구에서347m","매매가 60,000 ~ 120,000만원\n전세가 20,000 ~ 49,000만원","기본 옵션 없음",
        "#노원구쉐어하우스#여성전용쉐어하우스","쾌적하고 깔끔한 구성의 집입니다.서울여대 학생들이 많이 거주하며, 주변 치안도 좋습니다."))
        house_wal.add(Facility_info (R.drawable.img_home_sodambill,"[소담빌]","월세","B동 302호","서울시 노원구", 37.6197880321966, 127.07789245517912,
        "010-5378-7092","서울 노원구 노해로85길 10-55","노원역1번 출구에서203m","1,000만원/47만원 300만원/55만원 관리비 5만원 내외","세탁기 냉장고 식탁 등 풀옵션",
        "#노원역쉐어하우스#풀옵션","풀 옵션이며 보일러 작동 잘 됩니다. 연락주세요."))
        house_wal.add(Facility_info (R.drawable.img_home_chungsole,"[상계청솔아파트]","월세","125동 121호","서울시 노원구", 37.62337044501715, 127.08652253983522,
        "010-4374-8755","서울 노원구 상계로26길 20","상계역 버스 5분 내외","매매가 79,000만원","기본 옵션 없음",
        "#4호선상계역근처#남향#노원구쉐어하우스","바닥과 천장 벽을 황토로 마감하여 유해물질이 안 나옵니다."))
        house_wal.add(Facility_info (R.drawable.img_home_daeho,"[대호]","월세","805동 709호","서울시 노원구", 37.65998124324081, 127.07330162634443,
        "010-4180-3610 ","서울 노원구 한글비석로 384","상계역1번 출구에서97m","매매가 42,000 ~ 67,000만원전세가 24,000 ~ 35,000만원","기본 옵션 없음",
        "#노원구쉐어하우스#4호선상계역근처","층고가 상당히 높고, 복도식 아파트입니다."))
        house_wal.add(Facility_info (R.drawable.img_home_yumguang,"[염광]","월세","103동 705호","서울시 노원구",37.65689225680142, 127.07361639750847,
        "02-935-2612","서울 중랑구 망우로81길 27","양원역2번 출구에서619m","매매가38,000 ~ 43,000 만원","에어컨, 냉장고 옵션",
        "#망우역세어하우스#노원아파트","양원역 2번 출구에서 도보 10분 걸리에 위치한 아파트입니다.주변에 큰 규모의 근린공원이 있습니다."))
        house_wal.add(Facility_info (R.drawable.img_home_junggyegugong4,"[중계주공4단지]","월세","405동 306호","서울시 노원구", 37.65536512596464, 127.07704025518042,
        "02-938-9273","서울 노원구 덕릉로76길 29","상계역1번 출구에서817m","매매가 3,000만원","현관과 발코니, 다용도실 타일 시공 완료",
        "#28평아파트#노원구쉐어아파트","수납공간이 많아 여러명이서 지내기에도 좋습니다."))

        house_jun.add(Facility_info (R.drawable.img_home_shindonga,"[신동아]","전세","106동 904호","서울시 노원구", 37.66757191874165, 127.06754948968242,
        "02-951-3051","서울 노원구 중계로 184","불암초등학교에서 512m","전세가 4억7,000~5억","기본옵션없음",
        "#신동아아파트 #노원구쉐어하우스#즉시입주","거실 기준 남향으로 즉시입주 가능합니다."))
        house_jun.add(Facility_info (R.drawable.img_home_sanggyejugong9,"[상계주공9단지]","전세","912동 505호","서울시 노원구", 37.66296229077105, 127.06025586867261,
        "02-934-0147","서울 노원구 노원로 532","마들역4번 출구에서296m","전세가 2억~3억 5,000","지역난방/열병합",
        "#복도식#앞트인동#상계동쉐어하우스","온수근린공원과 도서관이 위치해있어 주변 입지가 좋습니다."))
        house_jun.add(Facility_info (R.drawable.img_home_sanggyejugong7,"[상계주공7단지]","전세","707동 202호","서울시 노원구",37.65904745825612, 127.0604076840165,
        "02-933-3644","서울 노원구 동일로 1456","노원역9번 출구에서347m","전세가 20,000 ~ 49,000 만원","냉장고 옵션",
        "#노원역쉐어하우스#즉시입주가능","노원역4호선~7호선 더블 역세권단지입니다.단지내 쾌적합니다."))
        house_jun.add(Facility_info (R.drawable.img_home_hanshin,"[한신]","전세","107동 905호","서울시 노원구", 37.66667123671229, 127.06722110893563,
        "02-973-5383","서울 노원구 한글비석로 151","하계역2번 출구에서976m","전세가 59,000 ~ 78,000 만원","욕실 2개",
        "#남향아파트#하계역쉐어하우스","하계역에서 매우 근접한 매물로 주변 치안이 좋습니다."))
        house_jun.add(Facility_info (R.drawable.img_home_byuksan,"[벽산]","전세","201동 514호","서울시 노원구", 37.661744612944695, 127.07235918462773,
        "02-939-3213","서울 노원구 한글비석로 399 벽산아파트","상계역3번 출구에서179m","전세가 14,500 ~ 42,000 만원","기본 옵션 없음",
        "#상계역쉐어하우스#노원구쉐어","상계역에서 도보 5분이내에 위치해있습니다.서울여대 학생들도 실제로 쉐어하우스로 많이 이용중입니다."))
        house_jun.add(Facility_info (R.drawable.img_home_anusbill,"[중계경남아너스빌]","전세","704동 404호","서울시 노원구", 37.66216395208811, 127.07683398149281,
        "02-972-5438","서울 노원구 덕릉로94길 10","상계역1번 출구에서490m","전세가 27,500 ~ 51,000 만원","기본 옵션 없음",
        "#상계역쉐어하우스#노원구쉐어하우스","계단식으로 이루어져 있으며 상계역에서 도보 5분거리에 위치해있습니다."))

        house_meme.add(Facility_info (R.drawable.img_home_joogong,"[상계주공7단지]","매매","107동 1103호","서울시 노원구", 37.65904745825612, 127.0604076840165,
        "02-933-3644","서울 노원구 동일로 1456","노원역9번 출구에서347m","매매가 60,000 ~ 120,000 만원","냉장고 옵션",
        "#노원역쉐어하우스\n#즉시입주가능","노원역4호선~7호선 더블 역세권단지입니다.단지내 쾌적합니다."))
        house_meme.add(Facility_info (R.drawable.img_home_ganyoung,"[건영2차]","매매","102동 705호","서울시 노원구", 37.662192432105265, 127.07676099349958,
        "02-971-2421","서울 노원구 덕릉로 517","중계역1번 출구에서271m","매매가 73,000 ~ 90,000 만원","기본 옵션 없음",
        "#중계역쉐어하우스\n#복도식","주변에 당현천과, 근린공원이 위치해있습니다."))
        house_meme.add(Facility_info (R.drawable.img_home_sung,"[성원]","매매","403동 104호","서울시 노원구", 37.66165701263498, 127.07519454607062,
        "02-951-1788","서울 노원구 덕릉로71길 5","중계역1번 출구에서833m","매매가 75,000 ~ 128,000 만원","에어컨 옵션",
        "#중계역쉐어하우스\n#노원구쉐어하우스","중계역 도보 10분 이내 위치하고 있으며, 주변 치안이 좋습니다."))
        house_meme.add(Facility_info (R.drawable.img_home_green,"[중계그린]","매매","113동 104호","서울시 노원구", 37.641805160445664, 127.06358972819615,
        "02-971-1064","서울 노원구 동일로207길 18","중계역4번 출구에서182m","매매가 52,000 ~ 76,000 만원","기본 옵션 없음",
        "#로보카폴리어린이교통공원#주변상가다수위치","중계역 도보 3분이내에 취이하고 있으며, 주변시설이 다양하게 위치해있습니다."))
        house_meme.add(Facility_info (R.drawable.img_home_hansin,"[한신3차]","매매","301동 209호","서울시 노원구", 37.666460017554265, 127.06735947819688,
        "02-935-0866","서울 노원구 한글비석로46가길 34","상계역3번 출구에서1078m","매매가 44,000 ~ 72,000 만원","기본 옵션 없음",
        "#무궁화공원#상계역쉐어하우스#노원구쉐어하우스","쾌적하고 깔끔한 구성의 집입니다. 서울여대 학생들이 많이 거주하며, 주변 치안도 좋습니다."))
        house_meme.add(Facility_info (R.drawable.img_home_sunglim,"[성림]","매매","901동 506호","서울시 노원구", 37.67321959228559, 127.0806631975005,
        "02-3391-3217","서울 노원구 덕릉로127길 25","당고개역5번 출구에서271m","매매가 40,000 ~ 49,000 만원","냉장고 기본 옵션",
        "#수락산당고개지구공원#노원쉐어하우스#당고개역쉐어하우스","지리적으로 조용한 곳에 위치해있습니다.\n당고개역에서 도보 4분거리 위치해있습니다."))
    }

}