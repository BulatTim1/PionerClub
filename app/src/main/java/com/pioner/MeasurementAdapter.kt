package com.pioner

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MeasurementAdapter(private val measurementlist : ArrayList<MeasurementClass>) : RecyclerView.Adapter<MeasurementAdapter.MeasurementHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.frame_measurement,parent,false)
        return MeasurementHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MeasurementHolder, position: Int) {
        val currentitem = measurementlist[position]
        holder.dataview.text = currentitem.Ldt
        holder.massview.text = currentitem.mass.toString() + " кг"
        holder.heightview.text = currentitem.height.toString() + " см"
        holder.caloriesview.text = currentitem.calories.toString() + " ккал"
    }

    override fun getItemCount(): Int {
        return measurementlist.size
    }
    class MeasurementHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dataview : TextView = itemView.findViewById(R.id.data_view)
        val massview : TextView = itemView.findViewById(R.id.mass_view)
        val heightview : TextView = itemView.findViewById(R.id.height_view)
        val caloriesview : TextView = itemView.findViewById(R.id.calories_view)
    }
}