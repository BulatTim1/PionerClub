package com.pioner.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pioner.R
import java.util.*
import kotlin.streams.asSequence

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_settings, container, false)
        val changePass = root.findViewById<Button>(R.id.change_pass2)
        val changeName = root.findViewById<Button>(R.id.change_Name)
        val addCode = root.findViewById<Button>(R.id.add_code)
        val role = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE).getInt("role", -1)
        if (role == 1){
            var flag = false
            var code = ""
            addCode.visibility = View.VISIBLE
            addCode.setOnClickListener {
                if(!flag) {
                    val source = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    code = Random().ints(8, 0, source.length)
                        .asSequence()
                        .map(source::get)
                        .joinToString("")
                    val db = Firebase.database.getReference("trainerPass")
                    db.push().setValue(code)
                    addCode.text = code
                    flag = true
                }
                val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("TrainerCode",code)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Скопировано", Toast.LENGTH_SHORT).show()
            }
        }
        val auth = Firebase.auth
        changePass.setOnClickListener {
            auth.sendPasswordResetEmail(auth.currentUser?.email.toString()).addOnSuccessListener {
                Toast.makeText(context, "Проверьте почту", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
        changeName.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.user_container, ChangeNameFragment()).addToBackStack( "login" )
                .commit()
        }
        return root
    }
}