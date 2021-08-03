package com.pioner

import android.widget.EditText
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(var email: String?, var name: String?, var  lastname: String?) {}
