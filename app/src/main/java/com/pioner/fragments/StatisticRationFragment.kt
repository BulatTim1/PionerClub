package com.pioner.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.pioner.MeasurementAdapter
import com.pioner.MeasurementClass
import com.pioner.R

class StatisticRationFragment : Fragment() {
    private lateinit var dbref: DatabaseReference
    private lateinit var rationRecyclerView: RecyclerView
    private lateinit var rationArrayList: ArrayList<MeasurementClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_statistic_ration, container, false)
        rationRecyclerView = root.findViewById(R.id.statictic_recycler)
        val addRation : Button = root.findViewById(R.id.go_ration_add_btn)
        val emptyText : TextView = root.findViewById(R.id.rationTextView)
        val rationStatistic: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rationRecyclerView.layoutManager = rationStatistic
        rationRecyclerView.setHasFixedSize(true)
        rationArrayList = arrayListOf()
        getRationData(emptyText)
        addRation.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.user_container, AddRationFragment()).addToBackStack(null).commit()
        }
        return root
    }

    private fun getRationData(emptyText : TextView) {
        val uid: String =
            requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                .getString("uid", "")
                .toString()
        dbref = FirebaseDatabase.getInstance().getReference("users").child(uid).child("measurements")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val measur = userSnapshot.getValue(MeasurementClass::class.java)
                        rationArrayList.add(measur!!)
                    }
                    rationRecyclerView.adapter = MeasurementAdapter(rationArrayList)
                }
                if(rationRecyclerView.isEmpty()){
                    emptyText.visibility = View.VISIBLE
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}