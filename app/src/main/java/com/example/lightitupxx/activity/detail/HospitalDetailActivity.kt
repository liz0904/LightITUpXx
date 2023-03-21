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
import kotlinx.android.synthetic.main.activity_hospitaldetail.*
import kotlin.properties.Delegates

class HospitalDetailActivity : AppCompatActivity() {

    lateinit var tv_name:TextView
    lateinit var tv_field:TextView
    lateinit var tv_location:TextView
    lateinit var tv_time:TextView
    lateinit var img_image:ImageView
    lateinit var tv_realtime:TextView
    lateinit var tv_phone:TextView
    lateinit var tv_ping:TextView
    lateinit var tv_way:TextView
    lateinit var tv_option:TextView
    lateinit var tv_hashtag:TextView
    lateinit var tv_comment:TextView
    lateinit var imageView: ImageView
    var longtitude by Delegates.notNull<Double>()
    var latitude by Delegates.notNull<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospitaldetail)

        tv_name=findViewById(R.id.tv_name)
        tv_field=findViewById(R.id.tv_field)
        tv_location=findViewById(R.id.tv_location)
        tv_time=findViewById(R.id.tv_time)
        img_image=findViewById(R.id.img_image)
        tv_realtime=findViewById(R.id.tv_realtime)
        tv_phone=findViewById(R.id.tv_phone)
        tv_ping=findViewById(R.id.tv_ping)
        tv_way=findViewById(R.id.tv_way)
        tv_option=findViewById(R.id.tv_option)
        tv_hashtag=findViewById(R.id.tv_hashtag)
        tv_comment=findViewById(R.id.tv_comment)
        imageView=findViewById(R.id.scrapHeart)

        if(intent.hasExtra("hospital")){
            var hospital=intent.getParcelableExtra<Facility_info>("hospital")
            tv_name.text=hospital?.name
            tv_field.text=hospital?.field
            tv_location.text=hospital?.location
            tv_time.text=hospital?.time
            img_image.setImageResource(hospital!!.image)

            tv_realtime.text=tv_time.text
            tv_phone.text=hospital?.phone
            tv_ping.text=hospital?.ping
            tv_way.text=hospital?.way
            tv_option.text=hospital?.option
            tv_hashtag.text=hospital?.hashtag
            tv_comment.text=hospital?.comment
            longtitude= hospital?.longtitude!!
            latitude = hospital?.latitude!!

        }else{
            Toast.makeText(this,"존재하지 않는 내용입니다.", Toast.LENGTH_SHORT).show()
        }

        tv_phone.setOnClickListener {
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data= Uri.parse("tel: 02-938-5557")
            startActivity(intent)
        }

        btn_call_hospital.setOnClickListener {
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data= Uri.parse("tel: 02-938-5557")
            startActivity(intent)
        }

        maphospitalBtn.setOnClickListener {
            var intent = Intent(this, FragmentMapActivity::class.java)
            intent.putExtra("longtitude", longtitude)
            intent.putExtra("latitude", latitude)

            startActivity(intent)
        }

        scraphospitalBtn.setOnClickListener {
            imageView.setImageResource(R.drawable.img_heart)
        }
    }
}
