package com.pioner.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.pioner.Exercises
import com.pioner.ExercisesAdapter
import com.pioner.R

class ExercisesFragment : Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var exercisesRecyclerView : RecyclerView
    private lateinit var userArrayList: ArrayList<Exercises>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_exercises, container, false)
        exercisesRecyclerView = root.findViewById(R.id.exerciseslist)
        val execTextView : TextView = root.findViewById(R.id.exercTextView)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        exercisesRecyclerView.layoutManager = layoutManager
        exercisesRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getUserData(execTextView)
        return root
    }

    private fun getUserData(execTextView : TextView) {
        val uid: String =
            requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                .getString("uid", "")
                .toString()
        dbref = FirebaseDatabase.getInstance().getReference("exercises").child("Standart")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val exer = userSnapshot.getValue(Exercises::class.java)
                        userArrayList.add(exer!!)
                    }
                    exercisesRecyclerView.adapter = ExercisesAdapter(userArrayList)
                }
                if(userArrayList.isEmpty()){
                    execTextView.visibility = View.VISIBLE
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}