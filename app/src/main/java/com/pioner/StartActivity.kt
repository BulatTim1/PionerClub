package com.pioner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (getSharedPreferences("user_pref", Context.MODE_PRIVATE).getString("uid", "") != "") startActivity(
            Intent(this, UserActivity::class.java)
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
}


