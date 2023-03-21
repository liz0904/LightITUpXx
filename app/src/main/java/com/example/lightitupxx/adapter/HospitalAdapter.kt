package com.example.lightitupxx.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lightitupxx.R
import com.example.lightitupxx.activity.detail.HospitalDetailActivity
import com.example.lightitupxx.api.Facility_info
import com.example.lightitupxx.api.onItemClicked
import java.util.*

class HospitalAdapter (val hospitalList : ArrayList<Facility_info>, private val onItemClicked: onItemClicked): RecyclerView.Adapter<HospitalAdapter.CustomViewHolder1>() {
    //뷰홀더가 처음 생성될때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder1 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_griditem_hospital, parent, false)
        return CustomViewHolder1(view)
    }
    //재활용해주는 곳 및 값을 넣어주는 곳
    override fun onBindViewHolder(holder: HospitalAdapter.CustomViewHolder1, position: Int) {
        holder.bindItems(hospitalList[position])
    }

    //리스트의 갯수를 적어준다
    override fun getItemCount(): Int {
        return hospitalList.size
    }

    //뷰홀더 클래스(음료수처럼 잡아주는 홀더)
    //이곳에서 파인드뷰아이디로 리스트 아이템에 있는 뷰들을 참조한다.
    inner class CustomViewHolder1(itemView : View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
            init {
                itemView.setOnClickListener {
                    onItemClicked.onGridItemClick(layoutPosition)
                }
            }

        fun bindItems(hospitals: Facility_info ){
            val h_Image = itemView.findViewById<ImageView>(R.id.imageView_hospital) //병원 종류
            val h_name = itemView.findViewById<TextView>(R.id.hospital_name)
            val h_time = itemView.findViewById<TextView>(R.id.hospital_time)
            val h_location = itemView.findViewById<TextView>(R.id.hospital_location)

            h_Image.setImageResource(hospitals.image)
            h_name.text = hospitals.name
            h_time.text = hospitals.time
            h_location.text = hospitals.location
        }
            override fun onClick(v: View?) {
                onItemClicked.onGridItemClick(layoutPosition)
            }

    }
}
