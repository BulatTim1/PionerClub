package com.pioner.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pioner.R
import java.util.*

class AddRationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_ration_add, container, false)
        val mass: EditText = root.findViewById(R.id.TextPersonMassRation)
        val height: EditText = root.findViewById(R.id.TextPersonHeightRation)
        val calories: EditText = root.findViewById(R.id.TextPersonСaloriesRation)
        val saveRation: Button = root.findViewById(R.id.SaveRationButton)
        val viewStatistic: Button = root.findViewById(R.id.ViewStatisticButton)
        val c: Calendar = Calendar.getInstance()
        val month = c.get(Calendar.MONTH).toString()
        val day = c.get(Calendar.DAY_OF_MONTH).toString()
        val year = c.get(Calendar.YEAR).toString()
        val table: DatabaseReference = Firebase.database("https://pionerclub-54483-default-rtdb.europe-west1.firebasedatabase.app").reference

        saveRation.setOnClickListener {
            val ldt = year + month + day
            if (mass.text.isEmpty() || height.text.isEmpty() || calories.text.isEmpty()) {
                Toast.makeText(context, "Вы не ввели все данные", Toast.LENGTH_LONG).show()
//            } else if (LdtInt >) {
//            Toast.makeText(context, "Вы уже отправляли данные сегодня", Toast.LENGTH_LONG).show()
            } else {
                val measurement = Measurement(mass.text.toString().toInt(), height.text.toString().toInt(), calories.text.toString().toInt(), ldt.toInt())
                val uid: String =
                    requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE).getString("uid", "")
                        .toString()
                if (uid != "") {
                    table.child("users").child(uid).child("measurements").push()
                        .setValue(measurement).addOnSuccessListener {
                            Toast.makeText(context, "Данные внесены", Toast.LENGTH_LONG).show()
                        }
                    mass.text.clear()
                    height.text.clear()
                    calories.text.clear()
                } else {
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }
        viewStatistic.setOnClickListener {
        }
        return root
    }
}


class Measurement(var mass: Int?, var height: Int?, var calories: Int?, var Ldt: Int?) {
}
