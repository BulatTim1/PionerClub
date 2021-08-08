package com.pioner.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.pioner.MeasurementClass
import com.pioner.R
import java.util.*

private lateinit var dbref: DatabaseReference

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
        val massView : TextView = root.findViewById(R.id.massMainView)
        val heightView : TextView = root.findViewById(R.id.heightMainView)
        val calView : TextView = root.findViewById(R.id.ccalMainView)
        val tipDay : TextView = root.findViewById(R.id.tipDayTextView)
        val massImage : ImageView = root.findViewById(R.id.MassImageView)
        val heightImage : ImageView = root.findViewById(R.id.HeightImageView)
        val calImage : ImageView = root.findViewById(R.id.CcalImageView)

        getRation(massView, heightView, calView, massImage, heightImage, calImage)
        getTipDay(tipDay)
        diaryDownBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment())
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

        setProgress( (0..100).random() , progressBar, progressText)
        return root
    }


    @SuppressLint("SetTextI18n")
    private fun setProgress(progress: Int, Bar : ProgressBar, Text : TextView) { //можно вставлять значения от 0 до 100
        val strProgress = "$progress %"
        Bar.progress = progress
        Text.text = "Завершено упражнений $strProgress"
    }

    private fun getRation(massView : TextView, heightView : TextView, calView : TextView, massImage : ImageView, heightImage : ImageView, calImage : ImageView) {
        val rationArrayList = arrayListOf<MeasurementClass>()
        val uid: String =
            requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                .getString("uid", "")
                .toString()
        dbref = FirebaseDatabase.getInstance().getReference("users").child(uid).child("measurements")
        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val measur = userSnapshot.getValue(MeasurementClass::class.java)
                        rationArrayList.add(measur!!)

                    }
                    if(rationArrayList.isNotEmpty()){
                        val mass: Int = rationArrayList.last().mass - rationArrayList.first().mass
                        val height: Int = rationArrayList.last().height - rationArrayList.first().height
                        var cal = 0
                        for (element in rationArrayList) {
                            cal += element.calories
                        }
                        cal /= rationArrayList.size
                        massView.text = "$mass кг"
                        heightView.text = "$height см"
                        calView.text = "$cal ккал"
                        when {
                            mass > 0 -> {
                                massImage.setImageResource(R.drawable.rise_ration)
                                massView.text = "+$mass кг"
                            }
                            mass < 0 -> {
                                massImage.setImageResource(R.drawable.downgrade_ration)
                            }
                            else -> massImage.setImageResource(R.drawable.without_changes_ration)
                        }
                        when {
                            height > 0 -> {
                                heightImage.setImageResource(R.drawable.rise_ration)
                                heightView.text = "+$height см"
                            }
                            height < 0 -> {
                                heightImage.setImageResource(R.drawable.downgrade_ration)
                            }
                            else -> heightImage.setImageResource(R.drawable.without_changes_ration)
                        }
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    private fun getTipDay( tipDay : TextView) {
        val tipArrayList = arrayListOf<String>()
        dbref = FirebaseDatabase.getInstance().getReference("tips")
        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val tip = userSnapshot.getValue(String()::class.java)
                        tipArrayList.add(tip!!)

                    }
                    val random: String = tipArrayList[Random().nextInt(tipArrayList.size)]
                    tipDay.text = random
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }
}
