package com.pioner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pioner.R
import com.pioner.databinding.ActivityStartBinding
import com.pioner.databinding.FragmentMessengerBinding


class MessengerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_messenger, container, false)
        val binding: FragmentMessengerBinding = FragmentMessengerBinding.inflate(inflater)
        return root
    }
}