package com.example.fitness.ui.login

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.data.FirebaseRepositoryImpl
import com.example.fitness.R
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: FirebaseRepositoryImpl,
    @ApplicationContext val context: Context
) : ViewModel() {
    private val _successLogin = MutableStateFlow(false)
    val successLogin = _successLogin.asStateFlow()

    fun validationEmailPassword(
        email: String,
        password: String
    ): Pair<Boolean, String> {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return Pair(
            false,
            context.getString(R.string.provideValidEmail)
        )
        if (password.isEmpty()) return Pair(false, context.getString(R.string.emptyPassword))
        if (password.length < 6) return Pair(false, context.getString(R.string.minPasswordLength))
        return Pair(true, context.getString(R.string.ok))
    }

    suspend fun loginUser(email: String, password: String)= withContext(Dispatchers.IO) {
        repository.loginUser(email, password) {
            _successLogin.value = it.isSuccessful
        }
    }
    fun getCurrentUser():FirebaseUser?{
       return repository.getCurrentUser()
    }
//    private fun fetchUserInfo(userID: String){
//        repository.fetchUserInfo(userID, object: ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                    val tmp: User = snapshot.getValue(User::class.java) ?: User()
//                    _user.value = tmp
//                }
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//        })
//    }
}