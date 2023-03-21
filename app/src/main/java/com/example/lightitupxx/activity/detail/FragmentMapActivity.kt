package com.example.lightitupxx.activity.detail

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lightitupxx.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.properties.Delegates


class FragmentMapActivity: AppCompatActivity(), OnMapReadyCallback {
    var longtitude by Delegates.notNull<Double>()
    var latitude by Delegates.notNull<Double>()
    lateinit var backButton:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_map)

        if(intent.hasExtra("longtitude")){
            longtitude = intent.getDoubleExtra("longtitude", 0.00)
            latitude = intent.getDoubleExtra("latitude", 0.00)
        }

        backButton = findViewById(R.id.img_mapBack)
        backButton.setOnClickListener {
            onBackPressed()
       }

        //지도 프래그먼트 설정
        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
        MapsInitializer.initialize(this);
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.d(TAG, "onMapReady: ");
        val LATLNG = LatLng(latitude, longtitude)

        googleMap.addMarker(
            MarkerOptions()
                .position(LATLNG)
        )
        val cameraPosition = CameraPosition.builder()
            .target(LATLNG)
            .zoom(18.0f)
            .build()

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}
