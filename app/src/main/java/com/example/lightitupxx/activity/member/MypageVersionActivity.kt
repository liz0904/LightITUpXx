package com.example.lightitupxx.activity.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.lightitupxx.R

class MypageVersionActivity : AppCompatActivity() {
    lateinit var imageversion : View
    lateinit var backButton : View
    lateinit var versionButton : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myversion)


        imageversion = findViewById(R.id.logo_version)
        backButton = findViewById(R.id.img_versionBack)
        versionButton = findViewById(R.id.button_VERSION)


        imageversion.background = getResources().getDrawable(R.drawable.backgroud_round, null)
        imageversion.setClipToOutline(true)

        versionButton.setOnClickListener {
            (Toast.makeText(this, "현재 최신 버전입니다.", Toast.LENGTH_SHORT)).show()
        }

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}