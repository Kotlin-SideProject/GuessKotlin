package com.angus.guesskotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angus.guesskotlin.data.Record
import kotlinx.android.synthetic.main.row_record.view.*

class RecordAdapter(var records : List<Record>) : RecyclerView.Adapter<RecordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_record, parent, false))
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
         holder.nickname.text = records.get(position).nickname
        holder.counter.text = records.get(position).count.toString()
    }

}

class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var nickname = itemView.record_nickname
    var counter = itemView.record_counter
}