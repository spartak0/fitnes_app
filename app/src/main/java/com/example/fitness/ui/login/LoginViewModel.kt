package com.example.fitness.ui.login

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FirebaseRepositoryImpl
import com.example.domain.FirebaseRepository
import com.example.fitness.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: FirebaseRepository,
    @ApplicationContext val context: Context,
) : ViewModel() {

    private val _error = MutableStateFlow(Pair(false, "ok"))
    val error = _error.asStateFlow()


    private fun validationEmailPassword(
        email: String,
        password: String
    ) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) _error.value = Pair(
            true,
            context.getString(R.string.provideValidEmail)
        )
        else if (password.isEmpty()) _error.value =
            Pair(true, context.getString(R.string.emptyPassword))
        else if (password.length < 6) _error.value =
            Pair(true, context.getString(R.string.minPasswordLength))
        else _error.value = Pair(false, context.getString(R.string.ok))
    }


    fun loginUser(
        email: String,
        password: String,
        navigate: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            validationEmailPassword(email, password)
            if (!_error.value.first) {
                repository.loginUser(email, password).addOnCompleteListener {
                    if (it.isSuccessful) navigate()
                    else {
                        _error.value = Pair(true, context.getString(R.string.userNotFound))
                    }
                }
            }
        }
    }


    fun onErrorClick() {
        _error.value = Pair(false, context.getString(R.string.ok))
    }
//    private fun fetchUserInfo(userID: String){
//        repository.fetchUserInfo(userID, object: ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                    val tmp: User = snapshot.getValue(User::class.java) ?: User()
//                    _user.value = tmp
//                }
//                override fun onCancelled(error: DatabaseError) {
//                }
//        })
//    }
}