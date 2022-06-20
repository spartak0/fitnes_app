package com.example.data

import com.example.domain.FirebaseRepository
import com.example.domain.models.User
import com.google.android.gms.tasks.NativeOnCompleteListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ValueEventListener

class FirebaseRepositoryImpl(
    private val firebaseService: FirebaseService
) : FirebaseRepository {
    override fun regUser(user: User, onCompleteListener: OnCompleteListener<AuthResult>) {
        firebaseService.regUser(user, onCompleteListener)
    }

    override fun loginUser(email: String, password: String, onCompleteListener: OnCompleteListener<AuthResult>){
        firebaseService.loginUser(email,password, onCompleteListener)
    }

    override fun fetchUserInfo(userID: String, valueEventListener: ValueEventListener) {
        firebaseService.fetchUserInfo(userID, valueEventListener)
    }

    override fun addUserInDatabase(user: User, userID: String, onCompleteListener: OnCompleteListener<Void>) {
        firebaseService.addUserInDatabase(user, userID, onCompleteListener)
    }

    override fun getCurrentUser(): FirebaseUser? {
       return firebaseService.getCurrentUser()
    }

}