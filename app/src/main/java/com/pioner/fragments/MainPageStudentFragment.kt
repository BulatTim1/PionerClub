package com.pioner.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pioner.R

class MainPageStudentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_main_page_student, container, false)
        val progressBar: ProgressBar = root.findViewById(R.id.ProgressCircleBar)
        val progressText: TextView = root.findViewById(R.id.ProgressCircleText)
        val diary_down_btn: Button = root.findViewById(R.id.diary_main_btn)
        val diary_top_btn: Button = root.findViewById(R.id.diary_btn)
        val setting_down_btn: Button = root.findViewById(R.id.setting_main_btn)
        val mess_down_btn: Button = root.findViewById(R.id.mess_main_btn)
        val exerc_down_btn: Button = root.findViewById(R.id.exerc_main_btn)
        val exerc_top_btn: Button = root.findViewById(R.id.continue_exercise_btn)
        val messenger_top_btn: Button = root.findViewById(R.id.messenger_btn)
        diary_down_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment())
                .commit()
        }
        diary_top_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment())
                .commit()
        }
        setting_down_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment())
                .commit()
        }
        mess_down_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, MessengerFragment())
                .commit()
        }
        exerc_down_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, ExercisesFragment())
                .commit()
        }
        exerc_top_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, ExercisesFragment())
                .commit()
        }
        messenger_top_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, MessengerFragment())
                .commit()
        }

        setProgress(57, progressBar, progressText)
        return root
    }


    @SuppressLint("SetTextI18n")
    private fun setProgress(progress: Int, Bar : ProgressBar, Text : TextView) { //можно вставлять значения от 0 до 100
        val strProgress = "$progress %"
        Bar.progress = progress
        Text.text = "Завершено упражнений $strProgress"
    }

}