package com.pioner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase


class TrainerAdapter(private val trainerList: ArrayList<Trainer>) :
    RecyclerView.Adapter<TrainerAdapter.MyViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_trainer, parent, false)
        context = parent.context
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = trainerList[position]
        holder.name.text = currentitem.name
        holder.title.text = currentitem.title
        holder.uid = currentitem.uid.toString()
        holder.context = context
    }

    override fun getItemCount(): Int {
        return trainerList.size
    }

    @SuppressLint("ResourceType")
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.trainer_name)
        val title: TextView = itemView.findViewById(R.id.trainer_title)
        var uid = ""
        lateinit var context: Context
        private val btn: Button = itemView.findViewById(R.id.pick_trainer_btn)

        init {
            btn.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "You clicked on item # ${position + 1}",
                    Toast.LENGTH_SHORT
                ).show()
                val table = FirebaseDatabase.getInstance().reference
                val uids = table.child("trainer_group").child(uid).get().toString()
                val myUid = itemView.context.getSharedPreferences("user_pref", Context.MODE_PRIVATE).getString("uid", "").toString()
                table.child("trainer_group").child(uid).setValue(uids + myUid)
                context.getSharedPreferences("user_pref", Context.MODE_PRIVATE).edit().putString("uid_trainer", uid).apply()
//                context.startActivity(Intent(, UserActivity::class.java), Bundle())
            }
        }
    }
}

