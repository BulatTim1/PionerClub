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
import com.pioner.MeasurementClass
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
        val calories: EditText = root.findViewById(R.id.TextPerson–°aloriesRation)
        val saveRation: Button = root.findViewById(R.id.SaveRationButton)
        val c: Calendar = Calendar.getInstance()
        var month = c.get(Calendar.MONTH).toString()
        var day = c.get(Calendar.DAY_OF_MONTH).toString()
        val year = c.get(Calendar.YEAR).toString()
        val table: DatabaseReference =
            Firebase.database("https://pionerclub-54483-default-rtdb.europe-west1.firebasedatabase.app").reference

        saveRation.setOnClickListener {
            if (month.toInt() < 10) {
                month = "0$month"
            }
            if (day.toInt() < 10) {
                day = "0$day"
            }
            val uid: String =
                requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    .getString("uid", "")
                    .toString()
            saveRation.setOnClickListener {
                val ldt = "$day/$month/$year"
                if (mass.text.isEmpty() || height.text.isEmpty() || calories.text.isEmpty()) {
                    Toast.makeText(context, "–í—č –Ĺ–Ķ –≤–≤–Ķ–Ľ–ł –≤—Ā–Ķ –ī–į–Ĺ–Ĺ—č–Ķ", Toast.LENGTH_LONG).show()
                } else if (mass.text.length > 3) {
                    Toast.makeText(context, "–Ě–Ķ–Ņ—Ä–į–≤–ł–Ľ—Ć–Ĺ—č–Ļ —Ą–ĺ—Ä–ľ–į—ā –ľ–į—Ā—Ā—č", Toast.LENGTH_LONG).show()
                } else if (height.text.length > 3) {
                    Toast.makeText(context, "–Ě–Ķ–Ņ—Ä–į–≤–ł–Ľ—Ć–Ĺ—č–Ļ —Ą–ĺ—Ä–ľ–į—ā —Ä–ĺ—Ā—ā–į", Toast.LENGTH_LONG).show()
                } else if (calories.text.length > 4) {
                    Toast.makeText(context, "–Ě–Ķ–Ņ—Ä–į–≤–ł–Ľ—Ć–Ĺ—č–Ļ —Ą–ĺ—Ä–ľ–į—ā –ļ–į–Ľ–Ľ–ĺ—Ä–ł–Ļ", Toast.LENGTH_LONG).show()
                } else
                {
                    val measurement = MeasurementClass( mass.text.toString().toInt(),  height.text.toString().toInt(),  calories.text.toString().toInt(), ldt )
                    if (uid != "") {
                        table.child("users").child(uid).child("measurements").push()
                            .setValue(measurement).addOnSuccessListener {
                                Toast.makeText(context, "–Ē–į–Ĺ–Ĺ—č–Ķ –≤–Ĺ–Ķ—Ā–Ķ–Ĺ—č", Toast.LENGTH_LONG).show()
                            }
                        mass.text.clear()
                        height.text.clear()
                        calories.text.clear()
                    } else {
                        Toast.makeText(context, "–ě—ą–ł–Ī–ļ–į", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        return root
    }
}

