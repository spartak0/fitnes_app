package com.example.data

import com.example.domain.FirebaseRepository
import com.example.domain.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class FirebaseRepositoryImpl(
    private val firebaseService: FirebaseService
) : FirebaseRepository {
    override fun regUser(email: String, password: String): Task<AuthResult> {
        return firebaseService.regUser(email, password)
    }

    override fun loginUser(email: String, password: String): Task<AuthResult> {
        return firebaseService.loginUser(email, password)
    }

    override fun addUserInDatabase(user: User, userID: String): Task<Void> {
        return firebaseService.addUserInDatabase(user, userID)
    }

    override fun getCurrentUser():FirebaseUser? {
        return firebaseService.getCurrentUser()
    }


}