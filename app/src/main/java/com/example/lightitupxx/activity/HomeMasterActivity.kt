package com.example.lightitupxx.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.lightitupxx.R
import com.example.lightitupxx.activity.category.HospitalListActivity
import com.example.lightitupxx.activity.detail.HospitalDetailActivity
import com.example.lightitupxx.api.Facility_info
import org.jetbrains.anko.startActivityForResult
import java.util.ArrayList

class HomeMasterActivity : AppCompatActivity() {

    lateinit var imageViewMaster : ImageView
    lateinit var button_reg : Button
    lateinit var edtTitle_master : EditText
    lateinit var edtTime_master : EditText
    lateinit var edtLocation_master : EditText
    lateinit var buttonMaster_Edit : Button
    lateinit var buttonMaster_Register : Button
    val hospital_san = ArrayList<Facility_info>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_master)

        imageViewMaster = findViewById(R.id.imageView_master)
        button_reg = findViewById(R.id.button_reg)
        edtTitle_master = findViewById(R.id.edtTitle_master)
        edtTime_master = findViewById(R.id.edtTime_master)
        edtLocation_master = findViewById(R.id.edtLocation_master)
        buttonMaster_Edit = findViewById(R.id.buttonMaster_Edit)
        buttonMaster_Register = findViewById(R.id.buttonMaster_Register)

        button_reg.setOnClickListener {openGallery()}
        buttonMaster_Register.setOnClickListener {
            val intentGoToHospitalListActivity = Intent(this, HospitalListActivity::class.java)
            intentGoToHospitalListActivity.putExtra("hospital",hospital_san)
            startActivity(intentGoToHospitalListActivity)
        }
    }

    private val GALLERY = 1
    private fun openGallery(){
        val intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent,GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode==Activity.RESULT_OK){
            if(requestCode==GALLERY){
                var currentImageUrl: Uri? = data?.data
                Toast.makeText(this,currentImageUrl.toString(), Toast.LENGTH_SHORT ).show()
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
                    imageViewMaster.setImageBitmap(bitmap)
                } catch (e:Exception)
                {
                    e.printStackTrace()
                }
            }
        }
    }
}