package com.pioner.fragments

import MessengerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pioner.Message
import com.pioner.R
import com.pioner.StartActivity
import com.pioner.databinding.FragmentMessengerBinding


class MessengerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_messenger, container, false)
        val b: FragmentMessengerBinding = FragmentMessengerBinding.inflate(inflater)
        val shpref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val db = Firebase.database
        val uid: String = shpref.getString("uid", "").toString()
        if (uid == "") {
            Toast.makeText(context, "Ошибка авторизации", Toast.LENGTH_LONG).show()
            startActivity(Intent(activity, StartActivity::class.java))
        }
        var uidTrainer = "viAdnQEQyFNcHJK4mbgRM5wfGpD3"
        db.getReference("users/${uid}/uid_trainer").get().addOnCompleteListener {
            if (it.isSuccessful) uidTrainer = it.result.toString()
        }
        if (uidTrainer == ""){
            Toast.makeText(context, "Настройте тренера", Toast.LENGTH_LONG).show()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.user_container, MainPageStudentFragment()).commit()
        }

        val msgList: RecyclerView = b.msgs
        b.msgSend.setOnClickListener {
            if (b.msgText.text.isEmpty()) Toast.makeText(context, "Вы не ввели все данные", Toast.LENGTH_LONG).show()
            else {
                val message = Message(
                    user = uid, msg = b.msgText.text.toString()
                )
                db.reference.child("messages").child(uidTrainer).child(uid).push()
                        //table.child("exercises").child("Standart").push()
                        .setValue(message).addOnSuccessListener {
                            Toast.makeText(context, "Данные внесены", Toast.LENGTH_LONG).show()
                        }
                    b.msgText.text.clear()
            }
        }
        updateDB(uid, uidTrainer, msgList)
        return root
    }

    private fun updateDB(uid: String, uidTrainer: String, msgList: RecyclerView){
        val db = Firebase.database
        val msgRef: DatabaseReference = db.getReference("messages/${uidTrainer}/${uid}")
        val msgDBList: ArrayList<Message> = arrayListOf()
        msgRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val msg = userSnapshot.getValue(Message::class.java)
                        msgDBList.add(msg!!)
                    }
                    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                    msgList.layoutManager = layoutManager
                    msgList.setHasFixedSize(true)
                    msgList.adapter = MessengerAdapter(msgDBList)
                }
            }
            override fun onCancelled(error: DatabaseError) {  }
        })
    }
}