package com.pioner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExercisesAdapter(private val exerciseslist: ArrayList<Exercises>) : RecyclerView.Adapter<ExercisesAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = exerciseslist[position]
        holder.nameEx.text = currentitem.nameExec
        holder.descriptionEx.text = currentitem.description
        holder.linkEx.text = currentitem.link
    }

    override fun getItemCount(): Int {
        return exerciseslist.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val nameEx : TextView = itemView.findViewById(R.id.nameExec)
        val descriptionEx : TextView = itemView.findViewById(R.id.description)
        val linkEx : TextView = itemView.findViewById(R.id.link)
    }
}