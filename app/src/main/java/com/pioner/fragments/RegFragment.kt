package com.pioner.fragments

import android.content.Context
import android.content.Intent
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pioner.R
import com.pioner.User
import com.pioner.UserActivity


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
        val regcode: EditText = root.findViewById(R.id.trainer_recode)
        val reg: Button = root.findViewById(R.id.reg_cont)
        val toLog: Button = root.findViewById(R.id.return_log_btn)
        val auth = FirebaseAuth.getInstance()
        val table: DatabaseReference =
            Firebase.database("https://pionerclub-54483-default-rtdb.europe-west1.firebasedatabase.app").reference

//        var adapter: ArrayAdapter? = ArrayList<String>
//        trennerChoose.setAdapter()

        reg.setOnClickListener {
            if (name.text.isEmpty() || lastname.text.isEmpty()) {
                Toast.makeText(context, "Неверное имя", Toast.LENGTH_LONG).show()
            } else if (!isEmail(email)) {
                Toast.makeText(context, "Неправильный email", Toast.LENGTH_LONG).show()
            } else if (pass.text.length < 8) {
                Toast.makeText(context, "Неверный пароль", Toast.LENGTH_LONG).show()
            } else if(regcode.text.toString() != "123"){
                Toast.makeText(context, "Неверный пароль тренера", Toast.LENGTH_LONG).show()
            } else {
                auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener(
                        requireActivity()
                    ) { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            val uid: String = task.result!!.user!!.uid
                            if (uid.isNotEmpty()) {
                                Log.d("Reg", "Reg UID: $uid")
                                val user = User(
                                    email.text.toString(),
                                    name.text.toString(),
                                    lastname.text.toString()
                                )

                                requireActivity().getSharedPreferences(
                                    "user_pref",
                                    Context.MODE_PRIVATE
                                ).edit().putString("uid", uid).apply()
                                table.child("users").child(uid).setValue(user)
                                    .addOnCompleteListener {
                                        if (it.isSuccessful) {
//                                parentFragmentManager.beginTransaction().replace(R.id.login_host, ConfirmationRegistrationFragment())
//                                    .commit()
                                            Toast.makeText(
                                                context,
                                                "Регистрация успешна",
                                                Toast.LENGTH_LONG
                                            )
                                                .show()
                                            name.text.clear()
                                            lastname.text.clear()
                                            email.text.clear()
                                            pass.text.clear()
                                            startActivity(
                                                Intent(
                                                    activity,
                                                    UserActivity::class.java
                                                )
                                            )
                                        } else {
                                            Log.e("DB", "DB: ${it.exception}")
                                            Toast.makeText(
                                                context,
                                                "Ошибка: ${it.exception}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                            }
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