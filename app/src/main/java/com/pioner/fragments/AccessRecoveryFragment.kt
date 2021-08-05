package com.pioner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.pioner.R
import com.pioner.databinding.FragmentAccessRecoveryBinding
import com.pioner.databinding.FragmentMessengerBinding

class AccessRecoveryFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_access_recovery, container, false)
        val returnBtn : Button = root.findViewById(R.id.return_log_recovery_btn)
        val continueBtn : Button = root.findViewById(R.id.continue_recovery_email_btn)
        val email : EditText = root.findViewById(R.id.access_mail)
        val auth = FirebaseAuth.getInstance()
        returnBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.login_host, LoginFragment())
                .commit()
        }
        continueBtn.setOnClickListener{
            if (email.text.isNotEmpty()) auth.sendPasswordResetEmail(email.text.toString()).addOnSuccessListener {
                email.text.clear()
                Toast.makeText(context, "Проверьте почту", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { Toast.makeText(context, it.message, Toast.LENGTH_LONG).show() }
            else Toast.makeText(context, "Вы не ввели почту!", Toast.LENGTH_SHORT).show()
//            parentFragmentManager.beginTransaction().replace(R.id.login_host, CodeRecoveryFragment())
//                .commit()
        }

        return root
    }
}