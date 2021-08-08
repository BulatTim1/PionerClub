package com.pioner.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pioner.R

class ChangeNameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_change_name, container, false)
        val completebtn: Button = root.findViewById(R.id.change_name_btn)
        val first: EditText = root.findViewById(R.id.name_field)
        val last: EditText = root.findViewById(R.id.surname_field)
        val uid = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE).getString("uid", "")
        val table: DatabaseReference = Firebase.database.getReference("users/${uid}")
        completebtn.setOnClickListener{
            if (first.text.isNotEmpty()){
                table.child("name").setValue(first.text.toString())
                first.text.clear()
            }
            if (last.text.isNotEmpty()){
                table.child("lastname").setValue(last.text.toString())
                last.text.clear()
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.user_container, SettingsFragment()).commit()
        }
        return root
    }
}