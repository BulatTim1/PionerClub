package com.pioner.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val mymanag: RecyclerView.LayoutManager = LinearLayoutManager(context)
        exercisesRecyclerView.layoutManager = mymanag
        //ExercisesRecyclerView.layoutManager = LinearLayoutManager(this)
        exercisesRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getUserData()
        return root
    }

    private fun getUserData() {
        val uid: String =
            requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                .getString("uid", "")
                .toString()
        dbref = FirebaseDatabase.getInstance().getReference("exercises").child(uid)
        //val dbref: DatabaseReference = Firebase.database("https://pionerclub-54483-default-rtdb.europe-west1.firebasedatabase.app").reference
        //dbref.child("exercises")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val exer = userSnapshot.getValue(Exercises::class.java)
                        userArrayList.add(exer!!)
                    }
                    exercisesRecyclerView.adapter = ExercisesAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}