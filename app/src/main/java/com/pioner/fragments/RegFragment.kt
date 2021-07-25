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
import com.pioner.R

class RegFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_reg, container, false)
        val name: EditText = root.findViewById(R.id.reg_name)
        val lastname: EditText = root.findViewById(R.id.reg_lastname)
        val email: EditText = root.findViewById(R.id.reg_email)
        val pass: EditText = root.findViewById(R.id.reg_pass)
        val reg: Button = root.findViewById(R.id.reg_btn)
        val toLog: Button = root.findViewById(R.id.reg_log)
        val auth = FirebaseAuth.getInstance()

        reg.setOnClickListener {
            if (name.text.isEmpty() || lastname.text.isEmpty()) {
                Toast.makeText(context, "Неверное имя", Toast.LENGTH_LONG).show()
            } else if (!isEmail(email)) {
                Toast.makeText(context, "Неправильный email", Toast.LENGTH_LONG).show()
            } else if (pass.text.length < 8) {
                Toast.makeText(context, "Неверный пароль", Toast.LENGTH_LONG).show()
            } else {
                auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener(
                        requireActivity()
                    ) { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            val token: String = task.result!!.user!!.uid
                            Log.d("Reg", "postDataToSQLite: $token")
//                                requireActivity().getSharedPreferences(
//                                    "token",
//                                    Context.MODE_PRIVATE
//                                ).edit()
//                                    .putString("token", token).apply()
//                                val db: FirebaseFirestore = FirebaseFirestore.getInstance()
//                                val doc: MutableMap<String, Any> = DBHelper.getDB()
//                                doc["username"] = username.getText().toString()
//                                doc["email"] = email.getText().toString()
//                                db.collection("users").document(token).set(doc)
//                                    .addOnCompleteListener { task1 ->
//                                        if (task1.isSuccessful()) {
//                                            Toast.makeText(context, "Успешно", Toast.LENGTH_LONG)
//                                                .show()
//                                        }
//                                    }
                            name.text.clear()
                            lastname.text.clear()
                            email.text.clear()
                            pass.text.clear()
//                                val fm = parentFragmentManager
//                                val ft = fm.beginTransaction()
//                                val fragment = MainFragment()
//                                ft.replace(R.id.host_fragment, fragment)
//                                ft.commit()
                        } else {
                            Toast.makeText(
                                context, task.exception!!.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
        toLog.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.login_host, LoginFragment())
                .commit()
        }
        return root
    }


    private fun isEmail(email: EditText?): Boolean {
        val value: String = email!!.text.toString()
        return value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}