package com.angus.guesskotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.angus.guesskotlin.data.GameDatabase
import com.angus.guesskotlin.data.Record
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecordActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_record)
        val count =  intent.getIntExtra("COUNTER", -1)
        counter.setText("第${count}次答對")
        save.setOnClickListener { view ->
            val nick = record_nickname.text.toString()
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER", count)
                .putString("REC_NICKNAME", nick)
                .apply()

            //Room Dao
            launch {
                GameDatabase.getInstance(this@RecordActivity)?.recordDao()?.insert(Record(nick, count))

            }
            val intent = Intent()
            intent.putExtra("NICKNAME", nick)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}
