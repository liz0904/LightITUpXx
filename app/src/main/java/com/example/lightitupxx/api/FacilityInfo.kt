package com.example.lightitupxx.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Facility_info (
    val image : Int,    //이미지
    val name : String,  //시설 이름
    val field : String, //종류
    val time : String = "평일 09시~20시",  //운영 시간
    val location :String = "서울시 노원구 은행사거리 국민은행 건물",   //위치
    val latitude:Double?,   //위도
    val longtitude:Double?, //경도
    var phone:String="",    //전화번호
    var ping:String="", //상세 주소
    var way:String="서울시 노원구 은행사거리 국민은행 건물",  //찾아가는 길
    var cost:String="없음",
    var option:String="#주차",   //시설 옵션(ex. 주차, 인터넷)
    var hashtag:String="#훌륭한 서비스 #여성 전용",  //해시 태그
    var comment:String="#여성 전용, 여성에 의한"   //그 외 업체 공지
    ) : Parcelable