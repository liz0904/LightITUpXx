package com.example.lightitupxx.activity.member

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.lightitupxx.R
import com.example.lightitupxx.activity.detail.FragmentMapActivity
import kotlinx.android.synthetic.main.activity_hospitaldetail.*
import kotlinx.android.synthetic.main.activity_scrap.*

class ScrapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrap)

        img_helpBack.setOnClickListener {
            onBackPressed()
        }
    }
}