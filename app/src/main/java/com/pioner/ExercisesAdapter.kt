package com.pioner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.pioner.fragments.AddRationFragment
import com.pioner.fragments.ExercisesInformation

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
    @SuppressLint("ResourceType")
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameEx : TextView = itemView.findViewById(R.id.nameExec)
        val descriptionEx : TextView = itemView.findViewById(R.id.description)
        val linkEx : TextView = itemView.findViewById(R.id.link)
        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item # ${position+1}",Toast.LENGTH_SHORT).show()
                val args = Bundle()
                args.putString("nameExec", nameEx.text.toString())
                args.putString("descriptionExec", descriptionEx.text.toString())
                args.putString("linkExec", linkEx.text.toString())
                val newFragment = ExercisesInformation()
                newFragment.setArguments(args)

                    //FragmentManager.beginTransaction().replace(R.layout.fragment_exercises, ExercisesInformation()).commit()
            }
        }
    }
}

