package com.pioner.fragments

import MessengerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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


class MessengerFragment : Fragment() {
    var trainerName = ""
    var userName = ""
    var uid = ""
    var uidTrainer = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_messenger, container, false)
//        val b: FragmentMessengerBinding = FragmentMessengerBinding.inflate(inflater)
        val btn: Button = root.findViewById(R.id.msgSend)
        val msg: EditText = root.findViewById(R.id.msgText)
        val msgList: RecyclerView = root.findViewById(R.id.msgs)
        val shpref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val db = Firebase.database
        uid = shpref.getString("uid", "").toString()
        if (uid == "") {
            Toast.makeText(context, "Ошибка авторизации", Toast.LENGTH_LONG).show()
            startActivity(Intent(activity, StartActivity::class.java))
        }
        uidTrainer = "viAdnQEQyFNcHJK4mbgRM5wfGpD3"
        db.getReference("users/${uid}/uid_trainer").get().addOnCompleteListener {
            if (it.isSuccessful) uidTrainer = it.result.toString()
        }
        if (uidTrainer == ""){
            Toast.makeText(context, "Настройте тренера", Toast.LENGTH_LONG).show()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.user_container, MainPageStudentFragment()).commit()
        }
        db.getReference("users/${uid}/name").get().addOnSuccessListener {
            userName = it.value.toString()
        }
        db.getReference("users/${uidTrainer}/name").get().addOnSuccessListener {
            trainerName = it.value.toString()
        }
        btn.setOnClickListener {
            if (msg.text.isEmpty()) Toast.makeText(context, "Пустое сообщение", Toast.LENGTH_LONG).show()
            else {
                val message = Message(
                    user = uid, msg = msg.text.toString()
                )
                db.reference.child("messages").child(uidTrainer).child(uid).push()
                        //table.child("exercises").child("Standart").push()
                        .setValue(message).addOnSuccessListener {
                            Toast.makeText(context, "Данные внесены", Toast.LENGTH_LONG).show()
                        }
                    msg.text.clear()
                msg.text.clear()
                updateDB(msgList)
            }
        }
        updateDB(msgList)
        return root
    }

    private fun updateDB(msgList: RecyclerView){
        val db = Firebase.database
        val msgRef: DatabaseReference = db.getReference("messages/${uidTrainer}/${uid}")
        val msgDBList: ArrayList<Message> = arrayListOf()
        msgRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val msg = userSnapshot.getValue(Message::class.java)
                        if(msg != null) {
                            when (msg.user) {
                                uid -> msg.user = userName
                                uidTrainer -> msg.user = trainerName
                                else -> {
                                    db.getReference("users/${msg.user}/name").get().addOnSuccessListener {
                                        userName = it.value.toString()
                                    }
                                }
                            }
                            msgDBList.add(msg)
                        }
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