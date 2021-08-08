package com.pioner.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pioner.R
import com.pioner.User

class ChangeNameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_settings, container, false)
        val completebtn: Button = root.findViewById(R.id.complete_recovery_btn)
        val auth = FirebaseAuth.getInstance()
//        val table: DatabaseReference =
//            Firebase.database("https://pionerclub-54483-default-rtdb.europe-west1.firebasedatabase.app").reference
//        completebtn.setOnClickListener{
//            auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
//                .addOnCompleteListener(
//                    requireActivity()
//                ) { task: Task<AuthResult> ->
//                    if (task.isSuccessful) {
//                        val uid: String = task.result!!.user!!.uid
//                        if (uid.isNotEmpty()) {
//                            Log.d("Reg", "Reg UID: $uid")
//                            val user = User(
//                                name.text.toString(),
//                                lastname.text.toString()
//                            )
//
//                            requireActivity().getSharedPreferences(
//                                "user_pref",
//                                Context.MODE_PRIVATE
//                            ).edit().putString("uid", uid).apply()
//                            table.child("users").child(uid).setValue(user)
//            val uid: String = task.result!!.user!!.uid
//
//        }
        return root
    }
}