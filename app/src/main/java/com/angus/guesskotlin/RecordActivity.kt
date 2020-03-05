package com.angus.guesskotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.angus.guesskotlin.data.GameDatabase
import com.angus.guesskotlin.data.Record
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            Thread(){
                GameDatabase.getInstance(this)?.recordDao()?.insert(Record(nick, count))
            }.start()

            val intent = Intent()
            intent.putExtra("NICKNAME", nick)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
