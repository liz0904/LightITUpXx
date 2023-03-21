package com.example.lightitupxx.activity.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.lightitupxx.R
import com.example.lightitupxx.activity.detail.LeisureDetailActivity
import org.jetbrains.anko.startActivity

class MypageReserveActivity : AppCompatActivity() {

    lateinit var backButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myreserve)

        backButton = findViewById(R.id.img_helpBack)

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}