package com.pioner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.getBooleanExtra("EXIT", false)) {
            finish()
        }
        if (getSharedPreferences("user_pref", Context.MODE_PRIVATE).getString("uid", "") != "") {
            val role = getSharedPreferences("user_pref", Context.MODE_PRIVATE).getInt("role", -1)
            if (role == 0) startActivity(Intent(this, UserActivity::class.java))
            else if (role == 1) startActivity(Intent(this, TrainerActivity::class.java))
        }
        setContentView(R.layout.activity_start)
    }
}


