package com.example.lightitupxx.activity.member

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lightitupxx.R
import com.example.lightitupxx.api.userClass
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_mypage.view.*
import org.jetbrains.anko.startActivity

class MypageActivity:AppCompatActivity(){

    lateinit var backButton:View
    lateinit var layoutName:View
    lateinit var layoutScrap:View
    lateinit var layoutMyinfo:View
    lateinit var layoutReserve:View
    lateinit var layoutCoupon: View
    lateinit var layoutVersion:View
    lateinit var layoutCS:View

    //로그인 Realm 인스턴스 얻기
    val loginRealm = try {
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.getInstance(config)
    } catch (ex: RealmMigrationNeededException) {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        layoutName=findViewById<ConstraintLayout>(R.id.layout_name)
        layoutScrap=findViewById<ConstraintLayout>(R.id.layout_scrap)
        layoutReserve=findViewById<ConstraintLayout>(R.id.layout_reserve)
        layoutCoupon=findViewById<ConstraintLayout>(R.id.layout_coupon)
        layoutMyinfo=findViewById<ConstraintLayout>(R.id.layout_myinfo)
        layoutCS=findViewById<ConstraintLayout>(R.id.layout_cs)
        layoutVersion=findViewById<ConstraintLayout>(R.id.layout_version)

        backButton = findViewById(R.id.img_helpBack)

        backButton.setOnClickListener {
            onBackPressed()
        }

        //Realm 데이터베이스 가져오기
        val user=loginRealm.where<userClass>().findFirst()
        if (user != null) {
            layoutName.tv_name.text= user.id
        }

        scrapLayoutEvent()
        myinfoLayoutEvent()
        reserveLayoutEvent()
        csLayoutEvent()
        couponLayoutEvent()
        versionLayoutEvent()
    }

    private fun scrapLayoutEvent(){
        layoutScrap.setOnClickListener {
            startActivity<ScrapActivity>()
        }
    }
    private fun myinfoLayoutEvent(){
        layoutMyinfo.setOnClickListener {
            startActivity<CheckmyinfoActivity>()
        }
    }
    private fun reserveLayoutEvent(){
        layoutReserve.setOnClickListener {
            startActivity<MypageReserveActivity>()
        }
    }
    private fun couponLayoutEvent(){
        layoutCoupon.setOnClickListener {
            startActivity<CouponActivity>()
        }
    }
    private fun csLayoutEvent(){
        layoutCS.setOnClickListener {
            startActivity<MypageCsActivity>()
        }
    }
    private fun versionLayoutEvent(){
        layoutVersion.setOnClickListener {
            startActivity<MypageVersionActivity>()
        }
    }
}