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
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_main_page_student, container, false)
        val progressBar: ProgressBar = root.findViewById(R.id.ProgressCircleBar)
        val progressText: TextView = root.findViewById(R.id.ProgressCircleText)
        val diaryDownBtn: Button = root.findViewById(R.id.diary_main_btn)
        val diaryTopBtn: Button = root.findViewById(R.id.diary_btn)
        val settingDownBtn: Button = root.findViewById(R.id.setting_main_btn)
        val messDownBtn: Button = root.findViewById(R.id.mess_main_btn)
        val exercDownBtn: Button = root.findViewById(R.id.exerc_main_btn)
        val exercTopBtn: Button = root.findViewById(R.id.continue_exercise_btn)
        val messengerTopBtn: Button = root.findViewById(R.id.messenger_btn)
        diaryDownBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment()).addToBackStack(null)
                .commit()
        }
        diaryTopBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment()).addToBackStack(null)
                .commit()
        }
        settingDownBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, SettingsFragment()).addToBackStack(null)
                .commit()
        }
        messDownBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, MessengerFragment()).addToBackStack(null)
                .commit()
        }
        exercDownBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, ExercisesFragment()).addToBackStack(null)
                .commit()
        }
        exercTopBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, ExercisesFragment()).addToBackStack(null)
                .commit()
        }
        messengerTopBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, MessengerFragment()).addToBackStack(null)
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