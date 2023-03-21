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
import com.example.lightitupxx.activity.detail.HospitalDetailActivity
import com.example.lightitupxx.adapter.FieldAdapter
import com.example.lightitupxx.adapter.HomeAdapter
import com.example.lightitupxx.activity.HomeActivity
import com.example.lightitupxx.adapter.HospitalAdapter
import com.example.lightitupxx.api.Facility_info
import com.example.lightitupxx.api.LocationItem
import com.example.lightitupxx.api.onItemClicked
import kotlinx.android.synthetic.main.activity_hospital_list.*
import kotlinx.android.synthetic.main.list_griditem_hospital.*
import java.util.*
import com.example.lightitupxx.adapter.HospitalAdapter as HospitalAdapter1

class HospitalListActivity : AppCompatActivity(), onItemClicked {
    lateinit var backButton: View
    lateinit var fieldAdapter: FieldAdapter
    lateinit var facilityAdapter_san: HospitalAdapter1
    lateinit var facilityAdapter_ne : HospitalAdapter1
    lateinit var facilityAdapter_pi : HospitalAdapter1
    lateinit var facilityAdapter_jung : HospitalAdapter1
    lateinit var facilityAdapter_chi : HospitalAdapter1
    var num : Int = 0
    var field_idx:Int=0

    val field = ArrayList<LocationItem>()
    val hospital_san = ArrayList<Facility_info>()
    val hospitals_ne = ArrayList<Facility_info >()
    val hospitals_pi= ArrayList<Facility_info >()
    val hospitals_ebi = ArrayList<Facility_info >()
    val hospitals_jung = ArrayList<Facility_info >()
    val hospitals_chi = ArrayList<Facility_info >()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_list)

        backButton = findViewById(R.id.img_hospitalPageBack)
        fieldAdapter = FieldAdapter(field)
        fieldlist_hospital.adapter = fieldAdapter
        fieldlist_hospital.setHasFixedSize(true) //어뎁터에 성능을 위한것
        //레이아웃 매니저를 이용해 어뎁터의 방향을 결정
        fieldlist_hospital.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        facilityAdapter_san = HospitalAdapter1(hospital_san, this)
        facilityAdapter_ne = HospitalAdapter1(hospitals_ne, this)
        facilityAdapter_pi = HospitalAdapter1(hospitals_pi, this)
        facilityAdapter_jung = HospitalAdapter1(hospitals_jung, this)
        facilityAdapter_chi = HospitalAdapter1(hospitals_chi, this)

        recyclerGridView_hospital.adapter=facilityAdapter_san
        recyclerGridView_hospital.layoutManager= GridLayoutManager(applicationContext,2)

        backButton.setOnClickListener { //뒤로가기
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

        var hospital=intent.getParcelableExtra<Facility_info>("hospital")
        imageView_hospital?.setImageResource(hospital!!.image)
        hospital_name?.text=hospital?.name
        hospital_time?.text=hospital?.time
        hospital_location?.text=hospital?.location
        hospital_san.add(Facility_info (R.drawable.san_hospital,"[플로체여성의원]","산부인과","평일 09시~17시","서울시 노원구",37.66523650214988, 127.05805302716998,
            "02-932-0700","서울 노원구 동일로 1530","","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분"))

    }

    //병원 리스트 아이템 클릭시 실행되는 함수
    override fun onGridItemClick(idx: Int) {
        Log.d("key", "gsg")
        val intentGoToHospitalDetail = Intent(this, HospitalDetailActivity::class.java)
        if(field_idx==0){
            intentGoToHospitalDetail.putExtra("hospital",hospital_san[idx])
        }else if(field_idx==1){
            intentGoToHospitalDetail.putExtra("hospital",hospitals_ne[idx])
        }else if(field_idx==2){
            intentGoToHospitalDetail.putExtra("hospital",hospitals_pi[idx])
        } else if(field_idx==4){
            intentGoToHospitalDetail.putExtra("hospital",hospitals_jung[idx])
        }else{
            Toast.makeText(this,"해당 필드가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
        }

        startActivity(intentGoToHospitalDetail)
    }

    private fun fieldCheck(v: View, position: Int){
        if(field[position].location=="산부인과"){
            recyclerGridView_hospital.adapter=facilityAdapter_san
            recyclerGridView_hospital.layoutManager= GridLayoutManager(applicationContext,2)
        }
        else if(field[position].location=="내과"){
            recyclerGridView_hospital.adapter=facilityAdapter_ne
            recyclerGridView_hospital.layoutManager= GridLayoutManager(applicationContext,2)
        }
        else if(field[position].location=="피부과") {
            recyclerGridView_hospital.adapter=facilityAdapter_pi
            recyclerGridView_hospital.layoutManager= GridLayoutManager(applicationContext,2)
        }
        else if(field[position].location=="정형외과"){
            recyclerGridView_hospital.adapter=facilityAdapter_jung
            recyclerGridView_hospital.layoutManager= GridLayoutManager(applicationContext,2)
        }

    }

    private fun setField(fieldAdapter: FieldAdapter) {
        field.add(LocationItem("산부인과"))
        field.add(LocationItem("내과"))
        field.add(LocationItem("피부과"))
        field.add(LocationItem("정형외과"))
    }

    private fun setHospitalsList(){
        //산부인과
        hospital_san.add(Facility_info (R.drawable.hospital,"[남미현산부인과의원]","산부인과","평일 09시~19시","서울시 노원구", 37.65440231559509, 127.06164452531382,
        "02-938-5557","서울 노원구 노해로 482","노원역 5번 출구에서 순복음교회쪽으로 올라오시면 우리은행 건물 7층에 위치하고 있습니다","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
            "노원역 롯데백화점 건너편에 위치하고 있습니다.","좋은 진료 좋은 서비스"))
        hospital_san.add(Facility_info (R.drawable.img_obhospital_park,"[박경숙산부인과]","산부인과","평일 09시~18시","서울시 노원구",37.65158089165931, 127.07594595381731,
            "02-933-9230","서울 노원구 한글비석로 253 세신프라자빌딩 2층","한글비석로에 위치하고 있습니다.","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분","공휴일은 휴무입니다.",  "친절한 상담으로 모시겠습니다."))
        hospital_san.add(Facility_info (R.drawable.img_obhospital_ab,"[에비뉴여성의원]","산부인과","평일 10시~19시","서울시 노원구",37.655383719291, 127.06075391479209,
        "02-936-0400", "서울 노원구 노해로 480","7호선 노원역 5번 출구 또는 4호선 2번 출구로 나와 롯데백화점 건너편, 버스정류장은 상계주공5단지","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
            "#소음순수술",
            "에비뉴는 대로, 가로수길의 의미입니다."))
        hospital_san.add(Facility_info (R.drawable.img_ophospital_piona,"[피오나여성의원]","산부인과","평일 09시~21시","서울시 노원구",37.656879850593164, 127.0634833136795,
        "02-951-7111","서울 노원구 상계로 77 다나플라자","노원역 10번 출구로 나오셔서 9번 출구 방향으로 5m 걸어 오시면 대로변 건물 (카페베네) 3층에 위치하고 있습니다.","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
                "#노원산부인과",
            "건강한 아름다움을 추구하는 피오나 여성의원은 진정한 아름아음을 만들어 드립니다."  ))
        hospital_san.add(Facility_info (R.drawable.img_obhospital_lee,"[이가영산부인과의원]","산부인과","평일 09~19시","서울시 노원구",37.65170617411463, 127.07741901367935,
        "02-952-0997","서울 노원구 한글비석로 270 스카이빌딩 4층","노원구 중계동 은행사거리 S-oil 주유소 옆","","주차",
            "#노원산부인과",
        "여의사진료, 일반부인과, 여성암검진, 경부암백신, 여성성형, 콘딜로마, 비만치료"))
        hospital_san.add(Facility_info (R.drawable.img_obhospital_gyu,"[박규희산부인과의원]","산부인과","평일 09시~17시","서울시 노원구",37.66523650214988, 127.05805302716998,
        "02-932-0700","서울 노원구 동일로 1530","","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분"))

        //내과
        hospitals_ne.add(Facility_info (R.drawable.img_imhospital_365,"[365열린의원]","내과","평일 09시~22시","서울시 노원구",37.641062120165884, 127.069936404806,
        "02-976-3658","서울 노원구 한글비석로 77 한성여객(주)","7호선 노원역 5번출구에서 20m거리-국민은행거리 옆 5층.","","주차, 예약, 남/녀 화장실 구분",
            "#대장내시경" ,
            "안녕하세요.좋은 진료 보장합니다."))
        hospitals_ne.add(Facility_info (R.drawable.img_imhospital_healthy,"[건강미소내과의원]","내과","평일 08시~19시","서울시 노원구",37.65438532734084, 127.06139256949666,
        "02-931-5513","서울 노원구 노해로 480 조광빌딩","7호선 노원역 5번출구에서 20m거리-신한은행 옆 5층.","","주차, 예약, 남/녀 화장실 구분",
        "#대장내시경" ,
        "안녕하세요. 건강미소내과의원 입니다. 방문하시면 영원한 주치의가 되어드립니다."))
        hospitals_ne.add(Facility_info (R.drawable.img_imhospital_kim,"[김앤박내과의원]","내과","평일 09시~18시","서울시 노원구",37.65317938234547, 127.06121281182358,
        "02-930-7601","서울 노원구 동일로 1392","지하철 7호선 5번출구 도보1분 상계주공 6단지 버스정류장 앞 다이소 빌딩 3층에 위치하고 있습니다.","","주차, 예약, 남/녀 화장실 구분",
        "#수면대장내시경" +
                "#수면위내시경",
        "반갑습니다 김앤박 내과입니다." ))
        hospitals_ne.add(Facility_info (R.drawable.img_imhospital_micro,"[태능마이크로병원]","내과","평일 09시~18시","서울시 노원구",37.61825946405877, 127.07472256392828,
        "02-970-0900","서울 노원구 동일로 987","지하철 6, 7호선 태릉입구역 2번 출구 앞, 공릉초등학교 정문 앞.","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
        "#진료",
        "수지접합, 미세혈관 및 신경복원술, 만성골반통 등 치료하는 병원."))
        hospitals_ne.add(Facility_info (R.drawable.img_imhospital_jaemin,"[제민통합내과정형외과의원]","내과","평일 09시~18시","서울시 노원구",37.656679351074736, 127.06198210018916,
        "02-936-6390","서울 노원구 상계로 63-7 청우빌딩 4층","4호선 노원역 9번 출구 - 아이맥스안경원 골목 우회전 - 할리스커피 건물 4층( 주공아파트 7단지 702동 앞)","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
        "토요일 진료 운영 : 물리치료 / 내시경 가능,"
        ))
        hospitals_ne.add(Facility_info (R.drawable.img_imhospital_samsung,"[삼성바른내과의원]","내과","평일 08시~18시","서울시 노원구",37.653929923983156, 127.07661157143266,
        "02-3392-2475","서울 노원구 한글비석로 270","중계동 은행사거리 S-oil 주요소 옆","","주차",
        "#노원내시경검사" ,
            "내시경검사, 초음파검사, 5대암검진, 종합검진센터, 여성검진센터 전문 병원"
        ))



        //피부과
        hospitals_pi.add(Facility_info (R.drawable.img_pshospital_doc,"[닥터쁘띠의원]","피부과","평일 10시~20시","서울시 노원구",37.656480587473816, 127.05896138317323,
        "02-3392-4285","서울 노원구 동일로 1396 중원빌딩 3층","노원역5번 출구에서75m","","",
        "#노원피부과",
        "예뻐지는 시간 beauty O'clock" ))
        hospitals_pi.add(Facility_info (R.drawable.img_pshospital_clean,"[클린업피부과의원]","피부과","평일 10시~20시","서울시 노원구",37.6680855126241, 127.05996424849391,
        "0507-1434-8289","서울 노원구 노해로 480 조광빌딩 8층","지하철 7호선 노원역 5번출구로 나오신 후 올리브영 건물 8층","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
        "키워드",
        "CU클린업피부과는 2001년 영등포점을 시작으로 해 국내 10개 지점으로 구성된 피부과전문의 네트워크 피부과입니다. "))
        hospitals_pi.add(Facility_info (R.drawable.img_pshospital_tox,"[톡스앤필의원]","피부과","평일 10시~20시","서울시 노원구",37.65640848314081, 127.06150553786279,
        "02-933-7585","서울 노원구 노해로 488 근호빌딩","4호선 이용 시 노원역 2번 출구, 7호선 이용 시 노원역 5번 출구로 나오시는 것이 오시기 편리합니다.", "","주차, 예약, 남/녀 화장실 구분",
        "#노원피부과" ,
        "빠르고 편리한 예약을 위해 전화 또는 홈페이지 예약 부탁드립니다 :)"))
        hospitals_pi.add(Facility_info (R.drawable.img_pshostpital_cha,"[차앤박피부과의원]","피부과","평일 10시~20시","서울시 노원구",37.655503164220896, 127.06315983014166,
        "0507-1331-0571","서울 노원구 노해로 495","노원역 4호선 2번 출구 또는 7호선 4번 출구로 나와 도보로 200m 이내 거리에 위치하고 있습니다.","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분",
        "#여드름흉터_SDRT",
                "차앤박피부과는 보다 나은 의료 서비스를 제공하기 위해 끊임없이 연구합니다."))
        hospitals_pi.add(Facility_info (R.drawable.img_shospital_abee,"[아비쥬의원]","피부과","평일 10시~20시","서울시 노원구",37.66245736271714, 127.0589201731129,
        "1544-0377","서울 노원구 동일로 1417 삼양빌딩 2,5층","","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분, 제로페이, 홈페이지",
        "#노원피부과" ,
        "예약문의: 카카오톡 @아비쥬노원"))
        hospitals_pi.add(Facility_info (R.drawable.img_pshospital_oaro,"[오아로피부과의원]","피부과","평일 10시~20시","서울시 노원구",37.65639382691609, 127.06447408353314,
        "02-930-1500","서울 노원구 노해로 507","지하철 4호선 노원역 1번출구에서 100m 직진 7호선 노원역 4번출구에서 150m 직진 하시면 됩니다.","","단체석, 주차, 예약, 무선 인터넷, 남/녀 화장실 구분, 장애인 편의시설, 지역화폐(지류형), 지역화폐(카드형), 지역화폐(모바일형), 제로페이",
                "#노원역피부과",
        "주차 시설 완비. 건물내 지하주차장 완비!" ))

        //정형외과
        hospitals_jung.add(Facility_info (R.drawable.img_oshospital_mady,"[노원마디본의원]","정형외과","평일 09시~20시","서울시 노원구",37.65310901675621, 127.06132995600616,
        "0507-1485-8278","서울 노원구 동일로 1392 한일빌딩 다이소 4층","노원마디본의원은 노원역 5번출구 50m앞 다이소 건물 4층에위치한 정형외과입니다.","","주차, 예약, 무선 인터넷, 남/녀 화장실 구분, 장애인 편의시설, 지역화폐(카드형), 제로페이",
        "#노원역정형외과" ,
        "척추관절클리닉, 도수치료클리닉, 체외충격파클리닉 비수술적시술클리닉"))
        hospitals_jung.add(Facility_info (R.drawable.img_oshospital_bang,"[방병원]","정형외과","평일 08시~17시","서울시 노원구",37.61849265152965, 127.07507101367848,
        "1577-4447","서울 노원구 동일로 989","6,7호선 태릉입구역 2번출구 바로 앞","","주차, 발렛파킹, 예약, 무선 인터넷, 남/녀 화장실 구분",
        "#조직재생신경성형술" ,
               "저희 병원은 관절, 척추, 뇌신경, 내과, 건강검진 을 하는 병원으로써"  ))
        hospitals_jung.add(Facility_info (R.drawable.img_oshospital_strong,"[굳쎈정형외과의원]","정형외과","평일 09시~18시","서울시 노원구",37.67802514845685, 127.0549906001897,
        "02-2038-4987","서울 노원구 동일로 1673 상계빌딩","","","","토요일 14시까지 진료"))
        hospitals_jung.add(Facility_info (R.drawable.img_hospital_sangmo,"[성모오케이정형외과의원]","정형외과","평일 09시~18시","서울시 노원구",37.65599236951852, 127.06733332535853,
        "031-523-3450","경기 구리시 갈매중앙로 91 한덕프라자","성모오케이정형외과는 갈매중앙로 한덕프라자 4층에 위치해 있습니다.","","주차",
        "#갈매정형외과" ,
        "진료과목:어깨관절, 무릎관절, 척추통증, 그외 근골격계 질환"))
        hospitals_jung.add(Facility_info (R.drawable.img_oshospital_nobos,"[서울노보스병원]","정형외과","평일 09시~18시","서울시 노원구",37.65483586359399, 127.06199025414544,
        "0507-1300-3250","서울 도봉구 도봉로 720","도봉구 방학역 1번 출구와 연결.", "","주차, 남/녀 화장실 구분",
                "#병원" +
                "#도봉구병원",
        "항상 가족과 같은 마음으로 정성과 최선을 다하겠습니다."))

    }




}
