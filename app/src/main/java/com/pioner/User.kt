package com.pioner

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(var email: String?, var name: String?, var  lastname: String?)
