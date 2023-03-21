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
import com.example.lightitupxx.activity.detail.LeisureDetailActivity
import com.example.lightitupxx.adapter.FieldAdapter
import com.example.lightitupxx.adapter.LeisureFacilityAdapter
import com.example.lightitupxx.api.Facility_info
import com.example.lightitupxx.api.LocationItem
import com.example.lightitupxx.api.onItemClicked
import kotlinx.android.synthetic.main.activity_leisure_facility_list.*
import java.util.ArrayList

class LeisureFacilityListActivity : AppCompatActivity(), onItemClicked {
    lateinit var backButton: View
    lateinit var fieldAdapter: FieldAdapter
    lateinit var facilityAdapter_wax: LeisureFacilityAdapter
    lateinit var facilityAdapter_tat: LeisureFacilityAdapter
    lateinit var facilityAdapter_health: LeisureFacilityAdapter
    lateinit var facilityAdapter_mass: LeisureFacilityAdapter
    var field_idx: Int = 0

    val field = ArrayList<LocationItem>()
    val leisure_wax = ArrayList<Facility_info>()
    val leisure_tat = ArrayList<Facility_info>()
    val leisure_health = ArrayList<Facility_info>()
    val leisure_mass = ArrayList<Facility_info>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leisure_facility_list)

        backButton = findViewById(R.id.img_leisurePageBack)
        fieldAdapter = FieldAdapter(field)
        fieldlist_leisure.adapter = fieldAdapter
        fieldlist_leisure.setHasFixedSize(true) //어뎁터에 성능을 위한것
        //레이아웃 매니저를 이용해 어뎁터의 방향을 결정
        fieldlist_leisure.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        facilityAdapter_wax = LeisureFacilityAdapter(leisure_wax, this)
        facilityAdapter_tat = LeisureFacilityAdapter(leisure_tat, this)
        facilityAdapter_health = LeisureFacilityAdapter(leisure_health, this)
        facilityAdapter_mass = LeisureFacilityAdapter(leisure_mass, this)

        recyclerGridView_leisure.adapter = facilityAdapter_wax
        recyclerGridView_leisure.layoutManager = GridLayoutManager(applicationContext, 2)

        backButton.setOnClickListener {
            onBackPressed()
        }

        fieldAdapter.setItemClickListener(object : FieldAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                field_idx = position

                Toast.makeText(
                    v.context,
                    field[position].location, Toast.LENGTH_SHORT
                ).show()
                fieldCheck(v, position)
            }
        })

        setField(fieldAdapter)
        setLeisureList()

    }


    //여가시설 리스트 아이템 클릭시 실행되는 함
    override fun onGridItemClick(idx: Int) {
        Log.d("key", "gsg")
        val intentGoToHomeDetail = Intent(this, LeisureDetailActivity::class.java)
        if (field_idx == 0) {
            intentGoToHomeDetail.putExtra("leisure", leisure_wax[idx])
        } else if (field_idx == 1) {
            intentGoToHomeDetail.putExtra("leisure", leisure_tat[idx])
        } else if (field_idx == 2) {
            intentGoToHomeDetail.putExtra("leisure", leisure_health[idx])
        } else if (field_idx == 3) {
            intentGoToHomeDetail.putExtra("leisure", leisure_mass[idx])
        } else {
            Toast.makeText(this, "해당 필드가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
        startActivity(intentGoToHomeDetail)
    }

    private fun fieldCheck(v: View, position: Int) {
        if (field[position].location == "왁싱") {
            recyclerGridView_leisure.adapter = facilityAdapter_wax
            recyclerGridView_leisure.layoutManager = GridLayoutManager(applicationContext, 2)
        } else if (field[position].location == "타투") {
            recyclerGridView_leisure.adapter = facilityAdapter_tat
            recyclerGridView_leisure.layoutManager = GridLayoutManager(applicationContext, 2)
        } else if (field[position].location == "헬스") {
            recyclerGridView_leisure.adapter = facilityAdapter_health
            recyclerGridView_leisure.layoutManager = GridLayoutManager(applicationContext, 2)
        } else if (field[position].location == "마사지") {
            recyclerGridView_leisure.adapter = facilityAdapter_mass
            recyclerGridView_leisure.layoutManager = GridLayoutManager(applicationContext, 2)
        }
    }

    private fun setField(fieldAdapter: FieldAdapter) {
        field.add(LocationItem("왁싱"))
        field.add(LocationItem("타투"))
        field.add(LocationItem("헬스"))
        field.add(LocationItem("마사지"))
    }

    private fun setLeisureList() {

        //왁싱샵
        leisure_wax.add(
            Facility_info(
                R.drawable.img_leisure_duie,
                "[두이에왁싱]",
                "왁싱",
                "평일 09시~20시",
                "서울시 노원구",
                37.67068315774175,
                127.08343358703314,
                "0507-1408-6807",
                "서울 강남구 선릉로 431 SK허브빌딩 지하1층 B112호",
                "없음",
                "",
                "주차: 건물내 주차 가능하며, 가실때 주차권 챙겨드립니다^^",
                "#강남왁싱",
                "회원권 없는 정직한 가격왁싱 전문 관리인이 끝까지 세심하게 관리해드리며 친절한 서비스로 최고의 만족을 드리겠습니다."
            )
        )
        leisure_wax.add(
            Facility_info(
                R.drawable.img_leisure_beauty,
                "[뷰티왁싱]",
                "왁싱",
                "평일 08시~22시",
                "서울시 노원구",
                37.67015801112936,
                127.08074449722707,
                "0507-1306-2417",
                "서울 노원구 동일로 1382 올림피아빌딩 2층 203호",
                "없음",
                "",
                "주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
                "#노원왁싱",
                "하지만 잘못된 왁싱 정보, 비위생적인 시술, 전문성 없는 교육.."
            )
        )
        leisure_wax.add(
            Facility_info(
                R.drawable.img_leisure_amelia,
                "[아멜리에왁싱]",
                "왁싱",
                "평일 10시~23시",
                "서울시 노원구",
                37.67025857140662,
                127.0795093509907,
                "010-7218-2220",
                "경북 포항시 북구 법원로11번길 24 3층",
                "",
                "",
                "주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
                "#메이크업",
                "포항 프리미엄 토탈뷰티샵&아카데미 '아멜리에'입니다"
            )
        )
        leisure_wax.add(
            Facility_info(
                R.drawable.img_leisure_sting,
                "[스팅턴왁싱]",
                "왁싱",
                "평일 10시~20시",
                "서울시 노원구",
                37.67314682791845,
                127.07953052491776,
                "0507-1306-2417",
                "서울 노원구 동일로 1382 올림피아빌딩 2층 203호",
                "없음",
                "",
                "주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
                "#노원왁싱",
                "하지만 잘못된 왁싱 정보, 비위생적인 시술, 전문성 없는 교육."
            )
        )
        leisure_wax.add(
            Facility_info(
                R.drawable.img_leisure_comeling,
                "[코멜리왁싱]",
                "왁싱",
                "평일 08시~20시",
                "서울시 노원구",
                37.67123064707739,
                127.08039865627306,
                "010-6471-6948",
                "서울 용산구 우사단로 36 정은빌딩 7층 709호",
                "없음",
                "",
                "무선 인터넷, 남/녀 화장실 구분",
                        "#슈가링왁싱",
                "COMELY sugaringwaxing은 왁싱 전 과정을"
            )
        )
        leisure_wax.add(
            Facility_info(
                R.drawable.img_leisure_vwaxing,
                "[여성전용V왁싱]",
                "왁싱",
                "평일 09시~20시",
                "서울시 노원구",
                37.67073902419987,
                127.07773779838097,
                "0507-1317-3540",
                "서울 종로구 종로 62 3층",
                "없음",
                "",
                "주차, 예약, 무선 인터넷, 남/녀 화장실 구분, 지역화폐(지류형), 지역화폐(카드형), 지역화폐(모바일형), 제로페이",
                        "#종로왁싱",
                "- 첫방문 20%~55% 할인 -"
            )
        )


        //타투샵
        leisure_tat.add(
            Facility_info(
                R.drawable.img_leisure_tob,
                "[토브타투]",
                "타투",
                "평일 09시~20시",
                "서울시 노원구",
                37.6722027099997,
                127.0794670031119,
                "0507-1398-0320",
                "서울 강북구 노해로 61 2층",
                "",
                "",
                "주차, 발렛파킹, 예약, 무선 인터넷",
                "",
                "10년의 경력으로 최선을다해"
            )
        )
        leisure_tat.add(
            Facility_info(
                R.drawable.img_leisure_baul,
                "[바울타투]",
                "타투",
                "평일 08시~20시",
                "서울시 노원구",
                37.67096248954809,
                127.08092800466008,
                "0507-1307-9565",
                "서울 강북구 한천로 1015-7 101호",
                "",
                "",
                "방문접수/출장, 예약",
                "키워드",
                "수유역 1번 출구에서 3분거리에 위치해 있는 아트작업실입니다."
            )
        )
        leisure_tat.add(
            Facility_info(
                R.drawable.img_leisure_gabano,
                "[가바노타투]",
                "타투",
                "평일 09시~20시",
                "서울시 노원구",
                37.66910491335868,
                127.08046923605959,
                "0507-1318-7176",
                "서울 강북구 노해로 61 2층",
            )
        )
        leisure_tat.add(
            Facility_info(
                R.drawable.img_leisure_hicari,
                "[히카리타투점]",
                "타투샵",
                "평일 10시~20시",
                "서울시 노원구",
                37.668945690381605,
                127.07887413292005,
                "0507-1403-9133",
                "서울 강북구 도봉로95길 29",
                "",
                "",
                "주차, 예약, 무선 인터넷, 반려동물 동반",
                "",
                "패션의 아이콘인 타투를"
            )
        )
        leisure_tat.add(
            Facility_info(
                R.drawable.img_leisure_with,
                "[위드타투]",
                "타투",
                "평일 08시~20시",
                "서울시 노원구",
                37.65904745825612,
                127.0604076840165,
                "010-9878-1721",
                "서울 강북구 덕릉로 110-1 지하1층",
                "",
                "",
                "주차, 예약, 무선 인터넷, 반려동물 동반",
                        "#수유타투",
                "예약문의: 낮은 자세에서 최선을 다하고 있습니다."
            )
        )
        leisure_tat.add(
            Facility_info(
                R.drawable.img_leisure_leathre,
                "[레더타투]",
                "타투",
                "평일 09시~20시",
                "서울시 노원구",
                37.66988146964927,
                127.07964698156509,
                "010-5427-3884",
                "서울 강북구 수유로 71 2층",
                "",
                "",
                "단체석, 주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
                "#타투",
                "수유 레더타투 입니다 타투문의/수강생문의/부스쉐어문의 받습니다"
            )
        )


        //헬스장
        leisure_health.add(
            Facility_info(
                R.drawable.ptdetail,
                "[커브스월계클럽]",
                "헬스",
                "평일 09시~20시",
                "서울시 노원구",
                37.668945690381605,
                127.07887413292005,
                "02-941-6330",
                "서울 성북구 월계로40길 7",
                "",
                "",
                "주차, 예약, 무선 인터넷, 남/녀 화장실 구분, 지역화폐(카드형), 지역화폐(모바일형), 제로페이",
                "#여성전용_헬스클럽",
                "여성만을 위한 30분 순환운동! 커브스!"
            )
        )
        leisure_health.add(
            Facility_info(
                R.drawable.img_leisure_cuves,
                "[커브스중계클럽]",
                "헬스",
                "평일 08시~20시",
                "서울시 노원구",
                37.671275339913386,
                127.08306304316511,
                "02-933-8080",
                "서울 노원구 중계로 225 청구상가 3층",
                "",
                "",
                "주차, 예약, 무선 인터넷, 남/녀 화장실 구분, 제로페이",
                "#30분순환운동",
                "여성만을 위한 30분 순환운동! 커브스!"
            )
        )
        leisure_health.add(
            Facility_info(
                R.drawable.img_leisure_gongreung,
                "[커브스공릉클럽]",
                "헬스",
                "평일 09시~20시",
                "서울시 노원구",
                37.671303272916845,
                127.08227254957382,
                "02-949-1330",
                "서울 노원구 노원로1길 9 4층 403호",
                "",
                "",
                "주차, 예약, 남/녀 화장실 구분",
                "#커브스",
                "여성만을 위한 30분 순환운동, 커브스!"
            )
        )
        leisure_health.add(
            Facility_info(
                R.drawable.img_leisure_jfit,
                "[Jfit여성전용PT샵]",
                "헬스",
                "평일 10시~20시",
                "서울시 노원구",
                37.65904745825612,
                127.0604076840165,
                "02-982-7707",
                "서울 강북구 삼양로 188 5층 제이핏",
                "",
                "",
                "주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
                "#헬스#체형교정",
                "여성들만을 위한 나만의공간에서 전문적인 트레이닝을 받을수 있습니다."
            )
        )
        leisure_health.add(
            Facility_info(
                R.drawable.img_leisure_onebodygym,
                "[원바디짐]",
                "헬스",
                "평일 08시~20시",
                "서울시 노원구",
                37.66988146964927,
                127.07964698156509,
                "0507-1410-7995",
                "경기 부천시 신흥로 250",
                "",
                "",
                "주차, 무선 인터넷, 남/녀 화장실 구분",
                "#헬스#부천PT",
                "원바디짐 원하는 몸 원하는 바디라인 전문 트레이너들이 직접 지도해드립니다!"
            )
        )
        leisure_health.add(
            Facility_info(
                R.drawable.img_leisure_ladyfit,
                "[레이디핏피트니스]",
                "헬스",
                "평일 09시~20시",
                "서울시 노원구",
                37.672373099056635,
                127.08012339511663,
                "032-553-0039",
                "인천 계양구 효서로 356 작전빌딩",
                "",
                "",
                "주차, 예약, 무선 인터넷",
                        "#여성전용헬스#작전동PT",
                "여성전용다이어트전문클럽 레이디핏"
            )
        )


        //마사지샵
        leisure_mass.add(
            Facility_info(
                R.drawable.img_leisure_queen,
                "[황후테라피]",
                "마사지",
                "평일 09시~20시",
                "서울시 노원구",
                37.672373099056635,
                127.08012339511663,
                "0507-1331-8821",
                "서울 성북구 아리랑로 7 해피엔딩오피스텔 지하2층",
                "",
                "",
                "단체석, 주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
                "#마사지",
                "항상 고객의 편안함을 위해 청결을 유지하는 황후 테라피입니다."
            )
        )
        leisure_mass.add(
            Facility_info(
                R.drawable.img_leisure_bundong,
                "[번동안마원]",
                "마사지",
                "평일 08시~20시",
                "서울시 노원구",
                37.671275339913386,
                127.08306304316511,
                "0508-202-6052",
                "서울 강북구 오현로31길 113-16",
                "",
                "",
                "방문접수/출장",
                "#아로마#강북강서강동",
                "전국 24시 출장 문의 주세요"
            )
        )
        leisure_mass.add(
            Facility_info(
                R.drawable.img_leisure_flow,
                "[폴로라에스테틱]",
                "마사지",
                "평일 09시~20시",
                "서울시 노원구",
                37.668945690381605,
                127.07887413292005,
                "02-547-5553",
                "서울 강남구 봉은사로 109 싼타홍메디컬타워 5층",
                "",
                "",
                "예약",
                "#강남에스테틱",
                "플로라를 방문해주신 고객분들 환영합니다.당신의 깨끗한 피부와 아름다운 몸매를 가꿔주는 고품격 관리!"
            )
        )
        leisure_mass.add(
            Facility_info(
                R.drawable.img_leisure_bls9,
                "[블라썸테라피]",
                "마사지",
                "평일 10시~20시",
                "서울시 노원구",
                37.65904745825612,
                127.0604076840165,
                "0507-1331-8821",
                "서울 성북구 아리랑로 7 해피엔딩오피스텔 지하2층",
                "",
                "",
                "단체석, 주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
                "#마사지#피부관리",
                "항상 고객의 편안함을 위해 청결을 유지하는 황후 테라피입니다."
            )
        )
        leisure_mass.add(
            Facility_info(
                R.drawable.img_leisure_wonly,
                "[여성전용케어샵]",
                "마사지",
                "평일 08시~20시",
                "서울시 노원구",
                37.66988146964927,
                127.07964698156509,
                "0508-202-6052",
                "서울 강북구 오현로31길 113-16",
                "",
                "",
                "방문접수/출장",
                "#아로마#강북강서강동",
                "전국 24시 출장 문의 주세요"
            )
        )
        leisure_mass.add(
            Facility_info(
                R.drawable.img_leisure_sgj,
                "[SGJ SPA]",
                "마사지",
                "평일 09시~20시",
                "서울시 노원구",
                37.671275339913386,
                127.08306304316511,
                "02-547-5553",
                "서울 강남구 봉은사로 109 싼타홍메디컬타워 5층",
                "",
                "",
                "예약",
                "#강남에스테틱",
                "플로라를 방문해주신 고객분들 환영합니다.당신의 깨끗한 피부와 아름다운 몸매를 가꿔주는 고품격 관리!"
            )
        )
    }
}