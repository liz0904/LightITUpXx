package com.example.lightitupxx.activity

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.lightitupxx.R
import com.example.lightitupxx.activity.member.LoginActivity


class MainActivity : AppCompatActivity() {

    internal var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)  //회원가입함수 구현 후 회원가입 엑티비티로 변경이 필요 --> 로그인 창으로 이동
            startActivity(intent)
            finish() }, 2000)

    }
}