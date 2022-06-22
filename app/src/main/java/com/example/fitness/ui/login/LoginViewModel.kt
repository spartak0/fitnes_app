package com.example.fitness.ui.login

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FirebaseRepositoryImpl
import com.example.fitness.R
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: FirebaseRepositoryImpl,
    @ApplicationContext val context: Context,
) : ViewModel() {

    private val _validation = MutableStateFlow(Pair(true, "ok"))
    val validation = _validation.asStateFlow()

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess = _validation.asStateFlow()

    private fun validationEmailPassword(
        email: String,
        password: String
    ) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) _validation.value = Pair(
            false,
            context.getString(R.string.provideValidEmail)
        )
        else if (password.isEmpty()) _validation.value =
            Pair(false, context.getString(R.string.emptyPassword))
        else if (password.length < 6) _validation.value =
            Pair(false, context.getString(R.string.minPasswordLength))
        else _validation.value = Pair(true, context.getString(R.string.ok))
    }


    fun getCurrentUser(): FirebaseUser? {
        return repository.getCurrentUser()
    }

    fun onClickLogin(email: String, password: String, navigate: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            validationEmailPassword(email, password)
            if (_validation.value.first) {
                repository.loginUser(email, password) {
                    _isSuccess.value = it.isSuccessful
                }
            }
            joinAll()
        }
        if (_isSuccess.value) navigate()
    }


    fun onErrorClick() {
        _validation.value = Pair(true, "ok")
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