package com.example.lightitupxx.activity.member

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lightitupxx.R
import com.example.lightitupxx.activity.HomeActivity
import com.example.lightitupxx.activity.HomeMasterActivity
import com.example.lightitupxx.api.userClass
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity: AppCompatActivity() {

    lateinit var etv_login_id:EditText
    lateinit var etv_login_pwd:EditText
    lateinit var btn_login:Button
    lateinit var tv_joining:TextView

    var handler: Handler? = null

    //로그인에 사용되는 RealmDB
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
        setContentView(R.layout.activity_login)

        rg1.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.radioButton1 ->
                    btn_login.setOnClickListener {
                        login_master()
                    }
                R.id.radioButton2 -> btn_login.setOnClickListener {
                    login()
                }
            }
        }

        etv_login_id=findViewById(R.id.etv_login_id)
        etv_login_pwd=findViewById(R.id.etv_login_pwd)
        btn_login=findViewById(R.id.btn_login)
        tv_joining=findViewById(R.id.tv_joining)

        handler = Handler()

        tv_joining.setOnClickListener {
            Toast.makeText(this,"회원가입 창으로 이동", Toast.LENGTH_SHORT).show()
            startActivity<JoinActivity>()
        }
    }

    fun login(){
        val id=etv_login_id.text.toString()
        val pwd=etv_login_pwd.text.toString()

        val person=loginRealm?.where(userClass::class.java)!!
            .equalTo("id", id)
            .equalTo("pwd", pwd)
            .findFirst()

        if(person!=null){
            Toast.makeText(this,"로그인 성공", Toast.LENGTH_SHORT).show()
            var intent=Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"로그인 정보가 없거나 틀립니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun login_master(){
        val id=etv_login_id.text.toString()
        val pwd=etv_login_pwd.text.toString()

        val person=loginRealm?.where(userClass::class.java)!!
            .equalTo("id", id)
            .equalTo("pwd", pwd)
            .findFirst()

        if(person!=null){
            Toast.makeText(this,"로그인 성공", Toast.LENGTH_SHORT).show()
            var intent=Intent(this, HomeMasterActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"로그인 정보가 없거나 틀립니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginRealm?.close()
    }
}