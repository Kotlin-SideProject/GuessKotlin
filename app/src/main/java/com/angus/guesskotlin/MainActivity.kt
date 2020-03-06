package com.angus.guesskotlin

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.row_function.view.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_CAMERA: Int = 100
    var TAG = "MainActivity"
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
        //Spinner
        var colors = listOf("Red", "Green", "Blue")
        var adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_item, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d(TAG, "onItemSelected: ${colors[position]}")
            }

        }
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
            0 -> {
                var permission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                if (permission == PackageManager.PERMISSION_GRANTED){
                    takePhoto()
                }else{
                    ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_CAMERA)
                }
            }
            1 -> startActivity(Intent(this, MaterialActivity::class.java))
            2 -> startActivity(Intent(this, RecordListActivity::class.java))
            else -> return
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_cache -> Log.d(TAG, "cache selected: ");
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_CAMERA){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePhoto()
            }
        }
    }

    private fun takePhoto() {
        startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    class FunctionHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nameText  = itemView.name
    }
}
