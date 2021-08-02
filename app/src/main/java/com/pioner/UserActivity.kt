package com.pioner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.pioner.databinding.ActivityUserBinding
import com.pioner.databinding.FragmentRationAddBinding
import com.pioner.fragments.AddRationFragment
import com.pioner.fragments.ExercisesAddFragment
import com.pioner.fragments.ExercisesFragment
import com.pioner.fragments.MessengerFragment

class UserActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = FirebaseAuth.getInstance()
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.messenger -> {
                    val messenger : MessengerFragment = MessengerFragment()
                    messenger.arguments = intent.extras
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, messenger).commit()
                    Toast.makeText(applicationContext, "Вход в мессенджер", Toast.LENGTH_SHORT).show()
                }
                R.id.ration -> {
                    val addration : AddRationFragment = AddRationFragment()
                    addration.arguments = intent.extras
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, addration).commit()
                    Toast.makeText(applicationContext, "Вход в дневник", Toast.LENGTH_SHORT).show()
                }
                R.id.settings -> {
                    val settings : AddRationFragment = AddRationFragment()
                    settings.arguments = intent.extras
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, settings).commit()
                    Toast.makeText(applicationContext, "Вход в настройки", Toast.LENGTH_SHORT).show()
                }
                R.id.exercises -> {
                    val exercises : ExercisesFragment = ExercisesFragment()
                    exercises.arguments = intent.extras
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, exercises).commit()
                    Toast.makeText(applicationContext, "Вход в Упражнения", Toast.LENGTH_SHORT).show()
                }
                R.id.addexercises -> {
                    val addexercises : ExercisesAddFragment = ExercisesAddFragment()
                    addexercises.arguments = intent.extras
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, addexercises).commit()
                    Toast.makeText(applicationContext, "Вход в Добавить упражнение. Функция для тренера", Toast.LENGTH_SHORT).show()
                }
                R.id.logout -> {
                    auth.signOut()
                    getSharedPreferences("user_pref", Context.MODE_PRIVATE).edit().remove("uid").apply()
                    startActivity(Intent(this, StartActivity::class.java))
                    Toast.makeText(applicationContext, "Вход в Упражнения", Toast.LENGTH_SHORT).show()
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