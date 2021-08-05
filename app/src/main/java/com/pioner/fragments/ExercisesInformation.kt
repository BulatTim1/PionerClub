package com.pioner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pioner.R

class ExercisesInformation : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_exercises_add, container, false)

        val b = arguments
        val name = b!!.getInt("nameExec")
        val description = b!!.getInt("descriptionExec")
        val link = b!!.getInt("nameExec")
        root.findViewById<TextView>(R.id.nameinfor).setText(name)
        root.findViewById<TextView>(R.id.descriptioninfo).setText(description)
        root.findViewById<TextView>(R.id.linkinfo).setText(link)
        return root
    }
}