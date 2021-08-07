package com.pioner.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.pioner.R

private lateinit var dbref: DatabaseReference

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
        val massView : TextView = root.findViewById(R.id.massMainView)
        val heightView : TextView = root.findViewById(R.id.heightMainView)
        val calView : TextView = root.findViewById(R.id.ccalMainView)
        var tipDay : TextView = root.findViewById(R.id.tipDayTextView)
        var MassImage : ImageView = root.findViewById(R.id.MassImageView)


//        val rationList : List<Int> = getRation()
//        massView.text = rationList[1].toString()
//        heightView.text = rationList[2].toString()
//        calView.text = rationList[3].toString()

        diary_down_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment())
                .commit()
        }
        diary_top_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment())
                .commit()
        }
        setting_down_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.user_container, SettingsFragment())
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

//    private fun getRation(): List<Int> {
//        val uid: String =
//            requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
//                .getString("uid", "")
//                .toString()
//        dbref = FirebaseDatabase.getInstance().getReference("users").child(uid).child("measurements")
//
//        var RationArrayList = arrayListOf<MeasurementClass>();
//
//        dbref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists()) {
//                    for (userSnapshot in snapshot.children) {
//                        val measur = userSnapshot.getValue(MeasurementClass::class.java)
//                        RationArrayList.add(measur!!)
//
//                    }
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//            }
//        })
//
//        val mass: Int = RationArrayList.last().mass - RationArrayList.first().mass
//        val height: Int = RationArrayList.last().height - RationArrayList.first().height
//        var cal: Int = 0
//        for ((index, element) in RationArrayList.withIndex()) {
//            cal += element.calories
//        }
//        cal /= RationArrayList.size
//
//        return listOf(mass, height, cal)
//
//    }

}
