package com.example.lightitupxx.activity.detail

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lightitupxx.R
import com.example.lightitupxx.api.Facility_info
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.activity_leisure_sale.*


class SaleLeisureActivity : AppCompatActivity(){
    private lateinit var qrimageView: ImageView
    lateinit var sale_image: ImageView
    lateinit var sale_name: TextView
    lateinit var sale_field: TextView
    lateinit var sale_location: TextView
    lateinit var sale_time: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leisure_sale)

        // initializing views
        qrimageView = findViewById(R.id.qr_image)

        val text = "할인코드"
        if (text.isNotBlank()) {
            val bitmap = generateQRCode(text)
            qrimageView.setImageBitmap(bitmap)
        }

        sale_name=findViewById(R.id.sale_name)
        sale_field=findViewById(R.id.sale_field)
        sale_location=findViewById(R.id.sale_location)
        sale_time=findViewById(R.id.sale_time)
        sale_image=findViewById(R.id.sale_image)

        if(intent.hasExtra("leisure")){
            var leisure=intent.getParcelableExtra<Facility_info>("leisure")
            sale_name.text=leisure?.name
            sale_field.text=leisure?.field
            sale_location.text=leisure?.location
            sale_time.text=leisure?.time
            sale_image.setImageResource(leisure!!.image)
        }else{
            Toast.makeText(this,"존재하지 않는 내용입니다.", Toast.LENGTH_SHORT).show()
        }

        img_saleBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun generateQRCode(text: String): Bitmap {
        val width = 300
        val height = 300
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.parseColor("#DEE2E6"))
                }
            }
        } catch (e: WriterException) { Log.d(TAG, "generateQRCode: ${e.message}") }
        return bitmap
    }
}