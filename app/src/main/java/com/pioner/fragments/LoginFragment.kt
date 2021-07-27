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
        val auth = FirebaseAuth.getInstance()
        toReg.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.login_host, RegFragment())
                .commit()
        }
        log.setOnClickListener {
            if (!isEmail(email)) {
                Toast.makeText(context, "Неправильная почта!", Toast.LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener(requireActivity()) { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            val token: String = task.result!!.user!!.uid
                            if (token.isNotEmpty()) {
                                requireActivity().getSharedPreferences(
                                    "user_pref",
                                    Context.MODE_PRIVATE
                                ).edit().putString("uid", token).apply()
                                Log.d("Login", "login token: $token")
                                email.text.clear()
                                pass.text.clear()
                                Toast.makeText(context, "Вход", Toast.LENGTH_LONG).show()
                                startActivity(Intent(activity, UserActivity::class.java))
                            }
                        } else {
                            Toast.makeText(context, "Не удалось войти", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            }
        }
        return root
    }

    private fun isEmail(email: EditText?): Boolean {
        val value: String = email!!.text.toString()
        return value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}