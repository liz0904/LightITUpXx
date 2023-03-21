package com.example.lightitupxx.activity.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lightitupxx.R

class MypageCsActivity : AppCompatActivity() {

    lateinit var backButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mycs)

        backButton = findViewById(R.id.img_csBack)

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}