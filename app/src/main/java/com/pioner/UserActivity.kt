package com.pioner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.pioner.databinding.ActivityUserBinding
import com.pioner.fragments.*

class UserActivity : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var b: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val uid = getSharedPreferences("user_pref", Context.MODE_PRIVATE).getString("uid", "")
        if (uid == "") {
            startActivity(
                Intent(this, StartActivity::class.java)
            )
        }
        super.onCreate(savedInstanceState)
        b = ActivityUserBinding.inflate(layoutInflater)
        setContentView(b.root)
        val auth = FirebaseAuth.getInstance()
        toggle = ActionBarDrawerToggle(this, b.drawerLayout, R.string.open, R.string.close)
        b.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.user_container, MainPageStudentFragment())
            .commit()
        b.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, MainPageStudentFragment())
                        .commit()
                }
                R.id.messenger -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, MessengerFragment())
                    .commit()
                }
                R.id.ration -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, StatisticRationFragment())
                        .commit()

                }
                R.id.settings -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, SettingsFragment())
                        .commit()
                }
                R.id.exercises -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, ExercisesFragment())
                        .commit()
                }
                R.id.addexercises -> {
                    supportFragmentManager.beginTransaction().replace(R.id.user_container, ExercisesAddFragment())
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

    override fun onBackPressed() {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}