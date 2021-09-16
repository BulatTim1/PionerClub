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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pioner.R
import com.pioner.UserActivity

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_login, container, false)
        val email: EditText = root.findViewById(R.id.log_email)
        val pass: EditText = root.findViewById(R.id.log_pass)
        val log: Button = root.findViewById(R.id.log_btn)
        val toReg: Button = root.findViewById(R.id.log_reg)
        val toRecovery: Button = root.findViewById(R.id.log_miss_pass)

        val auth = FirebaseAuth.getInstance()
        log.setOnClickListener {
            if (!isEmail(email)) {
                Toast.makeText(context, "Неправильная почта!", Toast.LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener(requireActivity()) { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            val uid: String = task.result!!.user!!.uid
                            if (uid.isNotEmpty()) {
                                Log.d("Login", "login token: $uid")
                                val role: Int = Firebase.database.getReference("users/${uid}/role").get().toString().toInt()
                                val uidTrainer: String = Firebase.database.getReference("users/${uid}/uid_trainer").get().toString()
                                requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE).edit().putString("uid", uid).putInt("role", role).putString("uid_trainer", uidTrainer).apply()
                                email.text.clear()
                                pass.text.clear()
                                Toast.makeText(context, "Вход", Toast.LENGTH_LONG).show()
                                startActivity(Intent(activity, UserActivity::class.java))
                            }
                        }
                        Toast.makeText(context, "Не удалось войти", Toast.LENGTH_LONG)
                            .show()
                    }
            }
        }
        toReg.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.login_host, RegFragment()).addToBackStack("login").commit()
        }
        toRecovery.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.login_host, AccessRecoveryFragment()).addToBackStack( "login" )
                .commit()
        }
        return root
    }

    private fun isEmail(email: EditText?): Boolean {
        val value: String = email!!.text.toString()
        return value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}