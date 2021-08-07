package com.pioner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.pioner.R

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_settings, container, false)
        val change_pass = root.findViewById<Button>(R.id.change_pass)
        change_pass.setOnClickListener() {
            parentFragmentManager.beginTransaction().replace(R.id.user_container, AccessRecoveryFragment())
                .commit()
        }
        return root
    }
}