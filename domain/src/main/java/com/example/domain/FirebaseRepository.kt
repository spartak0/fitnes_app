package com.example.domain

import android.content.Context
import com.example.domain.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

interface FirebaseRepository {
    fun regUser(user:User, onCompleteListener: OnCompleteListener<AuthResult>)
    fun loginUser(email:String, password:String, onCompleteListener: OnCompleteListener<AuthResult>)
    fun fetchUserInfo(userID: String, valueEventListener: ValueEventListener)
    fun addUserInDatabase(user: User, userID:String, onCompleteListener: OnCompleteListener<Void>)
    fun getCurrentUser():FirebaseUser?
}