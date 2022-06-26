package com.example.domain

import com.example.domain.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface FirebaseRepository {
    fun regUser(
        email: String,
        password: String
    ): Task<AuthResult>

    fun loginUser(
        email: String,
        password: String
    ): Task<AuthResult>

    fun addUserInDatabase(user: User, userID: String): Task<Void>
    fun getCurrentUser():FirebaseUser?
}