package com.pioner

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class ExercisesAdapter(private val exerciseslist: ArrayList<Exercises>) :
    RecyclerView.Adapter<ExercisesAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = exerciseslist[position]
        holder.nameEx.text = currentitem.nameExec
        holder.descriptionEx.text = currentitem.description
        holder.linkEx = currentitem.link.toString()
    }

    override fun getItemCount(): Int {
        return exerciseslist.size
    }

    @SuppressLint("ResourceType")
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameEx: TextView = itemView.findViewById(R.id.exercise_title)
        val descriptionEx: TextView = itemView.findViewById(R.id.exercise_body)
        private val btn: Button = itemView.findViewById(R.id.viewVideoBtn)
        var linkEx: String = ""

        init {
            btn.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "You clicked on item # ${position + 1}",
                    Toast.LENGTH_SHORT
                ).show()
                val videoId = linkEx.replace("https://www.youtube.com/watch?v=", "")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
                intent.putExtra("VIDEO_ID", videoId)
                itemView.context.startActivity(intent)
            }
        }
    }
}

