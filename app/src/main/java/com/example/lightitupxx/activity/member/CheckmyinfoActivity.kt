package com.example.lightitupxx.activity.member

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lightitupxx.R
import com.example.lightitupxx.api.userClass
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException
import io.realm.kotlin.where

class CheckmyinfoActivity : AppCompatActivity() {

    lateinit var backButton: View
    lateinit var name:TextView
    lateinit var withdrawal:View
    lateinit var withdrawal1:View

    //로그인 Realm 인스턴스 얻기
    val loginRealm = try {
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.getInstance(config)
    } catch (ex: RealmMigrationNeededException) {
        Realm.getDefaultInstance()
    }
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkmyinfo)

        backButton = findViewById(R.id.img_helpBack)
        name=findViewById(R.id.name)
        withdrawal=findViewById<ConstraintLayout>(R.id.checkmyinfo_Withdrawal)
        withdrawal1=findViewById<ConstraintLayout>(R.id.checkmyinfo_Withdrawal1)

        //Realm 데이터베이스 가져오기
        handler = Handler()
        val user=loginRealm.where<userClass>().findFirst()
        //유저 id 보여주기
        if (user != null) {
            name.text=user.id
        }

        //회원 탈퇴
        withdrawal.setOnClickListener{
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle(" 회원 탈퇴하시겠습니까? ")
            dialog.setMessage("확인 버튼을 누르실 경우, 회원님의 소중한\n개인정보가 모두 삭제됩니다.")
            dialog.setIcon(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)

            fun toast_p() {
                deleteTodo(name.text.toString())
            }
            fun toast_n(){
                super.onBackPressed()
            }

            var dialog_listener = object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(which){ //확인시 삭제
                        DialogInterface.BUTTON_POSITIVE ->
                            toast_p()
                        DialogInterface.BUTTON_NEGATIVE ->
                            toast_n()
                    }
                }
            }

            dialog.setPositiveButton("확인",dialog_listener)
            dialog.setNegativeButton("취소",dialog_listener)
            dialog.show()
        }

        backButton.setOnClickListener {
            onBackPressed()
        }

        withdrawal1.setOnClickListener {  //어플을 종료시킨다.
            finishAffinity()
            System.runFinalization()
            System.exit(0)}

    }

    // 데이터 베이스에서 계정 삭제
    private fun deleteTodo(id: String) {
        loginRealm.beginTransaction()
        val deleteItem = loginRealm.where<userClass>().equalTo("id", id).findFirst()!!
        deleteItem.deleteFromRealm()
        loginRealm.commitTransaction()

        finishAffinity() //어플을 종료시키고 로그인 페이지로 복귀
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        System.exit(0)
    }
}