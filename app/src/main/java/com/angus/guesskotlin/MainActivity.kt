package com.angus.guesskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_function.view.*

class MainActivity : AppCompatActivity() {
    val functions = listOf<String>(
        "Camera",
        "Guess Game",
        "Record",
        "Download coupons",
        "News",
        "Maps")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //RecyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = FunctionAdapter()
    }
    inner class FunctionAdapter : RecyclerView.Adapter<FunctionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionHolder {
            var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_function, parent, false)
            return FunctionHolder(view)
        }

        override fun getItemCount(): Int {
            return functions.size
        }

        override fun onBindViewHolder(holder: FunctionHolder, position: Int) {
//            Log.d("onBindViewHolder", "position:${position} ");
            holder.nameText.setText(functions.get(position))
            holder.itemView.setOnClickListener {
                functionClicked(position)
            }
        }

    }

    private fun functionClicked(position: Int) {
        when(position){
            1 -> startActivity(Intent(this, MaterialActivity::class.java))
            2 -> startActivity(Intent(this, RecordListActivity::class.java))
            else -> return
        }
    }

    class FunctionHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nameText  = itemView.name
    }
}
