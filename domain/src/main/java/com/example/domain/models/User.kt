package com.example.domain.models

import android.os.Parcelable
import android.provider.ContactsContract
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var firstname: String = "",
    var lastname: String = "",
    var gender: String = "",
    var birthDay: String = "",
    var weight: Int = 0,
    var height: Int = 0,
    var email: String = "",
    var password: String = "",
) : Parcelable
