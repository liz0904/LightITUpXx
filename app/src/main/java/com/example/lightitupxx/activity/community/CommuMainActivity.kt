package com.example.lightitupxx.activity.community

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.lightitupxx.R
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import io.realm.exceptions.RealmMigrationNeededException
import io.realm.kotlin.where
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class CommuMainActivity : AppCompatActivity() {

    lateinit var listView_commu: ListView
    lateinit var back_button: View

    val realm = try {
        //Realm 인스턴스 얻기
        //오류에 대비하여 예외처리
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.getInstance(config)
    } catch (ex: RealmMigrationNeededException) {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commu_main)
        back_button = findViewById(R.id.img_helpBack)
        listView_commu=findViewById(R.id.listView_commu)

        findViewById<FloatingActionButton>(R.id.fab_comu).setOnClickListener { view ->
            startActivity<CommuRegActivity>()
        }

        back_button.setOnClickListener {
            onBackPressed()
        }

        //데이터베이스 불러오기
        val realmResult = realm.where<CommuDB>().findAll().sort("id", Sort.ASCENDING)
        val adapter= CommuListAdapter(realmResult)
        listView_commu.adapter=adapter

        realmResult.addChangeListener { _ -> adapter.notifyDataSetChanged() }  // 데이터가 변경될 경우 어댑터에 적용됨
        // notifyDataSetChanged() : 데이터 변경을 통지하여 목록을 다시 출력함
        listView_commu.setOnItemClickListener { parent, view, position, id ->   // 리스트 뷰 아이템 클릭시 처리
            startActivity<CommuContentActivity>("id" to id)   // 기존 id 존재 여부에 따라 새 할 일 추가 또는 수정
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close() //데이터베이스 해제하기
    }
}