package com.example.lightitupxx.activity.member

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lightitupxx.R
import com.example.lightitupxx.api.userClass
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity: AppCompatActivity() {

    lateinit var etv_id:EditText
    lateinit var etv_pwd:EditText
    lateinit var etv_birth:EditText
    lateinit var btn_join:Button

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
        setContentView(R.layout.activity_join)

        etv_id=findViewById(R.id.etv_join_id)
        etv_pwd=findViewById(R.id.etv_join_pwd)
        etv_birth=findViewById(R.id.etv_join_birth)
        btn_join=findViewById(R.id.btn_join)

        handler = Handler()

        rg2.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.radioButton3 ->
                    joinMaster()
                R.id.radioButton4 ->
                    join()
            }
        }

        //회원가입 버튼 --> 주민번호 크기 잘못적으면 에러남
        btn_join.setOnClickListener {
            //여성인지 확인(주민번호 7번째 자리가 2 or 4)
            if (etv_birth.text.substring(6).equals("2") || etv_birth.text.substring(6).equals("4")) {
                join()
            }else{
                Toast.makeText(this,"해당 어플은 여성만 가입 가능합니다.",Toast.LENGTH_SHORT).show()
            }
        }

    }
    //회원가입 내용을 DB에 저장하는 함수
    private fun joinMaster(){
        loginRealm?.executeTransactionAsync {
            if (etv_id.text.isNotEmpty() && etv_pwd.text.isNotEmpty()) {
                handler?.post(Runnable {
                    Toast.makeText(this, "회원가입 성공!\n사업자 가입을 환영합니다.", Toast.LENGTH_SHORT).show()
                })
                val user = it.createObject(userClass::class.java)
                user.id = etv_id.text.toString()
                user.pwd = etv_pwd.text.toString()
                user.birth = etv_birth.text.toString()

                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                handler?.post(Runnable {
                    Toast.makeText(this, "아이디/비밀번호를 다시 확인하세요", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
    private fun join(){
        loginRealm?.executeTransactionAsync {
            if (etv_id.text.isNotEmpty() && etv_pwd.text.isNotEmpty()) {
                handler?.post(Runnable {
                    Toast.makeText(this, "회원가입 성공!\n회원 가입을 환영합니다.", Toast.LENGTH_SHORT).show()
                })
                val user = it.createObject(userClass::class.java)
                user.id = etv_id.text.toString()
                user.pwd = etv_pwd.text.toString()
                user.birth = etv_birth.text.toString()

                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                handler?.post(Runnable {
                    Toast.makeText(this, "아이디/비밀번호를 다시 확인하세요", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}