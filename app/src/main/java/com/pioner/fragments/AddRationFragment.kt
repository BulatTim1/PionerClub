package com.pioner.fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pioner.R
import java.time.LocalDateTime
import java.util.*

class AddRationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_ration_add, container, false)
        val mass: EditText = root.findViewById(R.id.TextPersonMassRation)
        val height: EditText = root.findViewById(R.id.TextPersonHeightRation)
        val сalories: EditText = root.findViewById(R.id.TextPersonСaloriesRation)
        val save_ration: Button = root.findViewById(R.id.SaveRationButton)
        val view_statistic: Button = root.findViewById(R.id.ViewStatisticButton)
        val auth = FirebaseAuth.getInstance()
        val c = Calendar.getInstance()
        val month = c.get(Calendar.MONTH).toString()
        val day = c.get(Calendar.DAY_OF_MONTH).toString()
        val year = c.get(Calendar.YEAR).toString()
        val table = Firebase.database.reference

        save_ration.setOnClickListener {
            val LdtString = year+month+day
            val LdtInt : Int = LdtString.toInt()
            if (mass.text.isEmpty() || height.text.isEmpty() || сalories.text.isEmpty() ) {
                Toast.makeText(context, "Вы не ввели все данные", Toast.LENGTH_LONG).show()
//            } else if (LdtInt >) {
//            Toast.makeText(context, "Вы уже отправляли данные сегодня", Toast.LENGTH_LONG).show()
            } else {
                auth.createUserWithEmailAndPassword(mass.text.toString(), height.text.toString())
                    .addOnCompleteListener(
                        requireActivity()
                    )
                    { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            val measurement1 = measurement( mass, height, сalories)
                            table.child("users").child(uid).child("measurements").push().setValue(measurement1)
//                            val token: String = task.result!!.user!!.uid
//                            Log.d("Reg", "postDataToSQLite: $token")

                            Toast.makeText(context, "Данные внесены", Toast.LENGTH_LONG).show()
                            mass.text.clear()
                            height.text.clear()
                            сalories.text.clear()
                        } else {
                            Toast.makeText(
                                context, task.exception!!.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
        view_statistic.setOnClickListener {

        }
        return root
    }
}

class measurement(mass: EditText, height: EditText, сalories: EditText) {

}
