package com.pioner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.pioner.R
class CreatingNewPassword : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_creating_new_password, container, false)
        val completeBtn : Button = root.findViewById(R.id.complete_recovery_btn)
        completeBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.login_host, LoginFragment())
                .commit()
        }
        return root
    }
}