package com.angus.guesskotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
          AlertDialog.Builder(this)
              .setTitle("Replay Game")
              .setMessage("Are you sure?")
              .setPositiveButton(getString(R.string.ok), {dialog, which ->
                  secretNumber.reset()
                  counter.setText("第${secretNumber.count}次")
                  ed_number.setText("")
              })
              .setNeutralButton("cancel", null)
              .show()
        }
        counter.setText("第${secretNumber.count}次")
        Log.d(TAG, "onCreate:  ${secretNumber.secret}" );

        val count =  getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", -1)
        val nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)
        Log.d(TAG, "onCreate:  ${count}/${nick}");
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart:" );

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume:" );

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart:" );

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause:" );

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop:" );

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy:" );

    }

    fun check(view : View){
        var n = ed_number.text.toString().toInt()
        println("number : $n")
        Log.d(TAG, "number  $n")
        val diff = secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)
        if (diff > 0){
            message = getString(R.string.smaller)
        }else if(diff < 0){
            message = getString(R.string.bigger)
        }
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), {dialog, which ->
               if(diff == 0){
                   val intent = Intent(this, RecordActivity::class.java)
                   intent.putExtra("COUNTER", secretNumber.count)
                   startActivity(intent)
               }
            })
            .show()
        counter.setText("第${secretNumber.count}次")
        ed_number.setText("")
    }

}
