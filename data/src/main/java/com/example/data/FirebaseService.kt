package com.example.data

import android.content.Context
import android.util.Log
import com.example.domain.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseService(
    private val context: Context
) {
    private val auth = Firebase.auth
    private val database = Firebase.database
    fun regUser(user: User, onCompleteListener: OnCompleteListener<AuthResult>) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener(onCompleteListener)
    }

    fun loginUser(
        email: String,
        password: String,
        onCompleteListener: OnCompleteListener<AuthResult>
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener (onCompleteListener)
    }

    fun fetchUserInfo(userID: String, valueEventListener: ValueEventListener) {
        database.getReference(context.getString(R.string.users)).child(userID)
            .addValueEventListener(valueEventListener)
    }

    fun addUserInDatabase(user: User, userID: String, onCompleteListener: OnCompleteListener<Void>) {
        database.getReference(context.getString(R.string.users)).child(userID).setValue(user)
            .addOnCompleteListener (onCompleteListener)
    }

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser
    }
}