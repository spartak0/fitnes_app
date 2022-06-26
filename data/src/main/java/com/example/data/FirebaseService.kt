package com.example.data

import android.content.Context
import com.example.domain.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseService(
    private val context: Context
) {
    private val auth = Firebase.auth
    private val database = Firebase.database

    fun regUser(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun loginUser(
        email: String,
        password: String
    ): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun fetchUserInfo(userID: String) {
        database.getReference(context.getString(R.string.users)).child(userID)
    }

    fun addUserInDatabase(
        user: User,
        userID: String
    ): Task<Void> {
        return database.getReference(context.getString(R.string.users)).child(userID).setValue(user)
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}