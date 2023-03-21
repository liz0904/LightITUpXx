package com.example.lightitupxx.activity.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lightitupxx.R
import com.example.lightitupxx.api.Facility_info
import kotlinx.android.synthetic.main.activity_leisuredetail.*
import kotlin.properties.Delegates

class LeisureDetailActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var tv_name: TextView
    lateinit var tv_field: TextView
    lateinit var tv_location: TextView
    lateinit var tv_time: TextView
    lateinit var img_image: ImageView
    lateinit var tv_realtime:TextView
    lateinit var tv_phone:TextView
    lateinit var tv_ping:TextView
    lateinit var tv_option:TextView
    lateinit var tv_hashtag:TextView
    lateinit var tv_comment:TextView

    var longtitude by Delegates.notNull<Double>()
    var latitude by Delegates.notNull<Double>()

    lateinit var leisure:Facility_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leisuredetail)

        tv_name=findViewById(R.id.tv_name)
        tv_field=findViewById(R.id.tv_field)
        tv_location=findViewById(R.id.tv_location)
        tv_time=findViewById(R.id.tv_time)
        img_image=findViewById(R.id.img_image)
        tv_realtime=findViewById(R.id.tv_realtime)
        tv_phone=findViewById(R.id.tv_phone)
        tv_ping=findViewById(R.id.tv_ping)
        tv_option=findViewById(R.id.tv_option)
        tv_hashtag=findViewById(R.id.tv_hashtag)
        tv_comment=findViewById(R.id.tv_comment)
        imageView=findViewById(R.id.scrapHeart)

        if(intent.hasExtra("leisure")){
            leisure= intent.getParcelableExtra<Facility_info>("leisure")!!
            tv_name.text=leisure?.name
            tv_field.text=leisure?.field
            tv_location.text=leisure?.location
            tv_time.text=leisure?.time
            img_image.setImageResource(leisure!!.image)

            tv_realtime.text=tv_time.text
            tv_phone.text=leisure?.phone
            tv_ping.text=leisure?.ping
            tv_option.text=leisure?.option
            tv_hashtag.text=leisure?.hashtag
            tv_comment.text=leisure?.comment

            longtitude= leisure?.longtitude!!
            latitude = leisure?.latitude!!


        }else{
            Toast.makeText(this,"존재하지 않는 내용입니다.", Toast.LENGTH_SHORT).show()
        }

        btn_sale_leisure.setOnClickListener {
            var intent = Intent(this, SaleLeisureActivity::class.java)
            intent.putExtra("leisure", leisure)
            startActivity(intent)
        }

        tv_phone.setOnClickListener {
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: 0507-")
            startActivity(intent)
        }

        mapleisureBtn.setOnClickListener {
            var intent = Intent(this, FragmentMapActivity::class.java)
            intent.putExtra("longtitude", longtitude)
            intent.putExtra("latitude", latitude)

            startActivity(intent)
        }


        scrapleisureBtn.setOnClickListener {
            imageView.setImageResource(R.drawable.img_heart)
        }
    }
}