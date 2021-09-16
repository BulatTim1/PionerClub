package com.pioner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.pioner.R
import com.pioner.Trainer
import com.pioner.TrainerAdapter

class SetTrainerFragment: Fragment() {
    private var dbref : DatabaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var trainerRv : RecyclerView
    private lateinit var userArrayList: ArrayList<Trainer>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_set_trainer, container, false)
        activity?.actionBar?.hide()
        trainerRv = root.findViewById(R.id.trainerRV)
        val noTrainersTextView : TextView = root.findViewById(R.id.noTrainersTextView)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        trainerRv.layoutManager = layoutManager
        trainerRv.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getUserData(noTrainersTextView)
        return root
    }

    private fun getUserData(execTextView : TextView) {
        var uidsTrainerStr = ""
        dbref.child("trainers").get().addOnCompleteListener {
            if(it.isSuccessful) {
                uidsTrainerStr = it.result.value.toString()
            }
        }
        val uidsTrainers: List<String> = uidsTrainerStr.split(",")
        for (i in uidsTrainers){
            val ref = dbref.child("users").child(i)
            ref.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(userSnapshot in snapshot.children){
                            val name = userSnapshot.child("name").value.toString()
                            val title = ""
                            userArrayList.add(Trainer(name, title, i))
                        }
                        trainerRv.adapter = TrainerAdapter(userArrayList)
                    }
                    if(userArrayList.isEmpty()){
                        execTextView.visibility = View.VISIBLE
                    }
                }
                override fun onCancelled(error: DatabaseError) {}
            })

        }
    }
}