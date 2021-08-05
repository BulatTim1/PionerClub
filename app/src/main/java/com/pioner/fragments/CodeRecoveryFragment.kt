package com.pioner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.pioner.R

class CodeRecoveryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_code_recovery, container, false)
        val continueBtn : Button = root.findViewById(R.id.continue_recovery_code_btn)
        continueBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.login_host, CreatingNewPassword())
                .commit()
        }
        return root
    }
}