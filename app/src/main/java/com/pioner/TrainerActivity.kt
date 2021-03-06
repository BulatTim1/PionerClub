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
import com.pioner.fragments.*

class TrainerActivity : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var b: ActivityTrainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val uid = getSharedPreferences("user_pref", Context.MODE_PRIVATE).getString("uid", "")
        val role = getSharedPreferences("user_pref", Context.MODE_PRIVATE).getInt("role", -1)
        if (uid == "" || role != 1) startActivity(Intent(this, StartActivity::class.java))
        super.onCreate(savedInstanceState)
        b = ActivityTrainerBinding.inflate(layoutInflater)
        setContentView(b.root)
        val auth = FirebaseAuth.getInstance()
        toggle = ActionBarDrawerToggle(this, b.drawerLayout, R.string.open, R.string.close)
        b.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.user_container, MessengerFragment())
            .commit()
        b.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.messenger -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, MessengerFragment()).addToBackStack(null)
                        .commit()
                }
                R.id.ration -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment()).addToBackStack(null)
                        .commit()

                }
                R.id.settings -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, SettingsFragment()).addToBackStack(null)
                        .commit()
                }
                R.id.exercises -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, ExercisesFragment()).addToBackStack(null)
                        .commit()
                }
                R.id.addexercises -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, ExercisesAddFragment()).addToBackStack(null)
                        .commit()
                    Toast.makeText(applicationContext, "???????? ?? ???????????????? ????????????????????. ?????????????? ?????? ??????????????", Toast.LENGTH_SHORT).show()
                }
                R.id.exercise_treiner -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, ExercisesForTrainerFragment()).addToBackStack(null)
                        .commit()
                    Toast.makeText(applicationContext, "???????? ?? ???????????????? ????????????????????. ?????????????? ?????? ??????????????", Toast.LENGTH_SHORT).show()
                }
                R.id.logout -> {
                    auth.signOut()
                    getSharedPreferences("user_pref", Context.MODE_PRIVATE).edit().remove("uid").remove("role").apply()
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