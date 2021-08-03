package com.pioner.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pioner.R
import kotlinx.android.synthetic.*

class ExercisesAddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_exercises_add, container, false)
        val nameExec: EditText = root.findViewById(R.id.name)
        val description: EditText = root.findViewById(R.id.description)
        val link: EditText = root.findViewById(R.id.link)
        val save: Button = root.findViewById(R.id.save)
        val table: DatabaseReference = Firebase.database("https://pionerclub-54483-default-rtdb.europe-west1.firebasedatabase.app").reference
        save.setOnClickListener {
            if (nameExec.text.isEmpty() || description.text.isEmpty() || link.text.isEmpty()) {
                Toast.makeText(context, "Вы не ввели все данные", Toast.LENGTH_LONG).show()
            } else {
                val exercises = exercises(
                    nameExec.text.toString(),
                    description.text.toString(),
                    link.text.toString(),
                )
                val uid: String =
                    requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                        .getString("uid", "")
                        .toString()
                if (uid != "") {
                    table.child("exercises").push()
                        .setValue(exercises).addOnSuccessListener {
                            Toast.makeText(context, "Данные внесены", Toast.LENGTH_LONG).show()
                        }
                    nameExec.text.clear()
                    description.text.clear()
                    link.text.clear()
                } else {
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }
        return root
    }
    @Suppress("ClassName")
    class exercises(var nameExec: String?, var description: String?, var link: String?) {
    }
}