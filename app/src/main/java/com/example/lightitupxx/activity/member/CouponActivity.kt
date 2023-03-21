package com.example.lightitupxx.activity.member

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lightitupxx.R

class CouponActivity: AppCompatActivity() {

    lateinit var back_button: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)
        back_button = findViewById(R.id.img_helpBack)

        back_button.setOnClickListener {
            onBackPressed()
        }
    }
}
