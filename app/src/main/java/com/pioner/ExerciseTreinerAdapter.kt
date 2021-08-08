package com.pioner

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class ExercisesTrainerAdapter(private val exerciseslist: ArrayList<Exercises>) :
    RecyclerView.Adapter<ExercisesTrainerAdapter.MyViewHolder>() {
    var Exer: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_item_for_trainer, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = exerciseslist[position]
        holder.nameExT.text = currentitem.nameExec
        holder.descriptionExT.text = currentitem.description
        holder.linkExT = currentitem.link.toString()
    }

    override fun getItemCount(): Int {
        return exerciseslist.size
    }

    @SuppressLint("ResourceType")
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Exer: MutableList<String> = ArrayList()
        val nameExT: TextView = itemView.findViewById(R.id.exercise_title_trainer)
        val descriptionExT: TextView = itemView.findViewById(R.id.exercise_body_trainer)
        private val btnVT: Button = itemView.findViewById(R.id.viewVideoBtnTrainer)
        private val btnT:Button = itemView.findViewById(R.id.addExInList)
        var linkExT: String = ""

        init {
            btnVT.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "You clicked on item # ${position + 1}",
                    Toast.LENGTH_SHORT
                ).show()
                val videoId = linkExT.replace("https://www.youtube.com/watch?v=", "")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
                intent.putExtra("VIDEO_ID", videoId)
                itemView.context.startActivity(intent)
            }
            btnT.setOnClickListener{
                var NameExe = nameExT.text
                Toast.makeText(
                    itemView.context,
                    NameExe.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                Exer.add(NameExe.toString())
                System.out.println(Exer)
                //нада в массив занести, а в следующем фрагменте вывести в виде listview
            // и сбоку добавить мелкие EditText для количества выполнения упражнений
            }
        }
    }
}
