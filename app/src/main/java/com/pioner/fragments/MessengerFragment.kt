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
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
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
        val btn: Button = root.findViewById(R.id.msgSend)
        val msg: EditText = root.findViewById(R.id.msgText)
        val msgList: RecyclerView = root.findViewById(R.id.msgs)
        val title: TextView = root.findViewById(R.id.trennerName)
        val shpref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val db = Firebase.database
        uid = shpref.getString("uid", "").toString()
        if (uid == "") {
            Toast.makeText(context, "Ошибка авторизации", Toast.LENGTH_LONG).show()
            startActivity(Intent(activity, StartActivity::class.java))
        }
        uidTrainer = "viAdnQEQyFNcHJK4mbgRM5wfGpD3"
        db.getReference("users/${uid}/uid_trainer").get().addOnCompleteListener {
            if (it.isSuccessful) uidTrainer = it.result.value.toString()
        }
        if (uidTrainer == "") {
            Toast.makeText(context, "Настройте тренера", Toast.LENGTH_LONG).show()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.user_container, MainPageStudentFragment()).commit()
        }
        db.getReference("users/${uid}/name").get().addOnSuccessListener {
            userName = it.value.toString()
        }
        db.getReference("users/${uidTrainer}/name").get().addOnSuccessListener {
            trainerName = it.value.toString()
        }
        title.text = trainerName
        updateDB(db, msgList)
        btn.setOnClickListener {
            if (msg.text.isEmpty()) Toast.makeText(context, "Пустое сообщение", Toast.LENGTH_LONG)
                .show()
            else {
                val message = Message(
                    user = uid, msg = msg.text.toString()
                )
                db.reference.child("messages").child(uidTrainer).child(uid).push()
                    .setValue(message).addOnSuccessListener {
                        Toast.makeText(context, "Данные внесены", Toast.LENGTH_LONG).show()
                    }
                msg.text.clear()
                updateDB(db, msgList)
            }
        }
        return root
    }

    private fun updateDB(db: FirebaseDatabase, msgList: RecyclerView){
        val msgRef: DatabaseReference = db.getReference("messages/${uidTrainer}/${uid}")
        val msgDBList: ArrayList<Message> = arrayListOf()
        msgRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val msgDB = userSnapshot.getValue(Message::class.java)
                        if (msgDB != null) {
                            when (msgDB.user) {
                                uid -> msgDB.user = userName
                                uidTrainer -> msgDB.user = trainerName
                                else -> {
                                    db.getReference("users/${msgDB.user}/name").get()
                                        .addOnSuccessListener { name ->
                                            userName = name.value.toString()
                                        }
                                }
                            }
                            msgDBList.add(msgDB)
                        }
                    }
                    val layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    msgList.layoutManager = layoutManager
                    msgList.setHasFixedSize(true)
                    msgList.adapter = MessengerAdapter(msgDBList)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}