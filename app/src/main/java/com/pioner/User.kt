package com.pioner

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class User {
    var email: String? = null
    var name: String? = null
    var lastname: String? = null

    constructor() {}

    constructor(email: String?, name: String?, lastname: String?) {
        this.email = email
        this.name = name
        this.lastname = lastname
    }

}
