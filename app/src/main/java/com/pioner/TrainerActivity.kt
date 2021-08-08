package com.pioner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.pioner.databinding.ActivityTrainerBinding
import com.pioner.databinding.ActivityUserBinding
import com.pioner.fragments.*

class TrainerActivity : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var b: ActivityTrainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val uid = getSharedPreferences("user_pref", Context.MODE_PRIVATE).getString("uid", "")
        if (uid == "") {
            startActivity(
                Intent(this, StartActivity::class.java)
            )
        }
        super.onCreate(savedInstanceState)
        b = ActivityTrainerBinding.inflate(layoutInflater)
        setContentView(b.root)
        val auth = FirebaseAuth.getInstance()
        toggle = ActionBarDrawerToggle(this, b.drawerTrainerLayout, R.string.open, R.string.close)
        b.drawerTrainerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.user_trainer_container, MainPageStudentFragment())
            .commit()
        b.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_trainer_container, MainPageStudentFragment()).addToBackStack(null)
                        .commit()
                }
                R.id.messenger -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_trainer_container, MessengerFragment()).addToBackStack(null)
                        .commit()
                }
                R.id.ration -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_trainer_container, StatisticRationFragment()).addToBackStack(null)
                        .commit()

                }
                R.id.settings -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_trainer_container, SettingsFragment()).addToBackStack(null)
                        .commit()
                }
                R.id.exercises -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_trainer_container, ExercisesFragment()).addToBackStack(null)
                        .commit()
                }
                R.id.addexercises -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_trainer_container, ExercisesAddFragment()).addToBackStack(null)
                        .commit()
                    Toast.makeText(applicationContext, "Вход в Добавить упражнение. Функция для тренера", Toast.LENGTH_SHORT).show()
                }
                R.id.exercise_treiner -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_trainer_container, ExercisesForTrainerFragment()).addToBackStack(null)
                        .commit()
                    Toast.makeText(applicationContext, "Вход в Добавить упражнение. Функция для тренера", Toast.LENGTH_SHORT).show()
                }
                R.id.logout -> {
                    auth.signOut()
                    getSharedPreferences("user_pref", Context.MODE_PRIVATE).edit().remove("uid").apply()
                    startActivity(Intent(this, StartActivity::class.java))
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}