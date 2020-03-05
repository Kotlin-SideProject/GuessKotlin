package com.angus.guesskotlin

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.angus.guesskotlin.data.GameDatabase
import kotlinx.android.synthetic.main.activity_record_list.*

class RecordListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)

        //get records from database
        AsyncTask.execute {
            var records = GameDatabase.getInstance(this)?.recordDao()?.getAll()
            records?.let {
                runOnUiThread {
                    recycler.layoutManager = LinearLayoutManager(this)
                    recycler.setHasFixedSize(true)
                    recycler.adapter = RecordAdapter(it) }
            }
        }
    }
}
