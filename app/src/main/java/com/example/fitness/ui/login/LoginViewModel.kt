package com.example.fitness.ui.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.FirebaseRepository
import com.example.fitness.R
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: FirebaseRepository,
    @ApplicationContext val context: Context,
) : ViewModel() {

    private val _error = MutableStateFlow(Pair(false, "ok"))
    val error = _error.asStateFlow()

    private val _waitVerification = MutableStateFlow(getCurrentUser()!=null)
    val waitVerification = _waitVerification.asStateFlow()


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
                    if (it.isSuccessful) {
                        navigate()
                    } else {
                        _error.value = Pair(true, it.exception?.message!!)
                    }
                }
            }
        }
    }


    fun onErrorClick() {
        _error.value = Pair(false, context.getString(R.string.ok))
    }

    fun signOut() {
        repository.signOut()
    }

    fun verifiedOnClickOk(navigate: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCurrentUser()?.let {
                it.reload().addOnCompleteListener { it2 ->
                    run {
                        if (it2.isSuccessful)
                            if (it.isEmailVerified) {
                                navigate()
                                _waitVerification.value=false
                            } else Toast.makeText(context, "not verified", Toast.LENGTH_LONG)
                    }
                }
            }
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return repository.getCurrentUser()
    }

    fun verifiedOnClickCancel() {
        repository.deleteCurrentUserFromAuth()?.addOnCompleteListener {
            if (it.isSuccessful) Log.d("AAA", "deleteCurrentUserFromAuth: success")
            else Log.d("AAA", "deleteCurrentUserFromAuth: ${it.exception?.message}")
        }
        repository.deleteUserFromBase(getCurrentUser()!!.uid)
        _waitVerification.value=false
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