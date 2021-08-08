package com.pioner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pioner.R

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_settings, container, false)
        val changePass = root.findViewById<Button>(R.id.change_pass)
        val auth = Firebase.auth
        changePass.setOnClickListener {
            auth.sendPasswordResetEmail(auth.currentUser?.email.toString()).addOnSuccessListener {
                Toast.makeText(context, "Проверьте почту", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
        return root
    }
}