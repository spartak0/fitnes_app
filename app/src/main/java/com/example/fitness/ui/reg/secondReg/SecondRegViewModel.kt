package com.example.fitness.ui.reg.secondReg

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.FirebaseRepository
import com.example.domain.models.User
import com.example.fitness.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondRegViewModel @Inject constructor(
    private val repository: FirebaseRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _error = MutableStateFlow(Pair(false, context.getString(R.string.ok)))
    val error = _error.asStateFlow()

    private val _waitVerification = MutableStateFlow(false)
    val waitVerification = _waitVerification.asStateFlow()

    private fun validationTest(
        user: User
    ) {
        if (user.gender.isEmpty())
            _error.value =
                Pair(
                    true,
                    context.getString(R.string.chooseGender)
                )
        else if (user.birthDay.isEmpty()) _error.value =
            Pair(true, context.getString(R.string.writeValidBirthday))
        else if (user.weight == 0) _error.value =
            Pair(true, context.getString(R.string.validWeight))
        else if (user.height == 0) _error.value =
            Pair(true, context.getString(R.string.validHeight))
        else _error.value = Pair(false, context.getString(R.string.ok))
    }

    fun onErrorClick() {
        _error.value = Pair(false, context.getString(R.string.ok))
    }

    fun regUser(user: User, navigate: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            validationTest(user)
            if (!_error.value.first) {
                repository.regUser(user.email, user.password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        sendEmailVerification()
                        addUserInDatabase(user, repository.getCurrentUser()!!.uid)
                        _waitVerification.value = true
                    } else _error.value = Pair(true, it.exception?.message.toString())
                }
            }
        }
    }

    private fun sendEmailVerification() {
        repository.sendEmailVerification()?.addOnCompleteListener {
            if (it.isSuccessful) Log.d("AAA", "sendEmailVerification: success")
            else Log.d("AAA", "sendEmailVerification: not success")
        }
    }

    private fun addUserInDatabase(user: User, uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUserInDatabase(user, uid).addOnCompleteListener {
                if (it.isSuccessful) Log.d("AAA", "addUserInDatabase: success")
                else Log.d("AAA", "addUserInDatabase: not success")
            }
        }
    }

    fun verifiedOnClick(navigate: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCurrentUser()?.let {
                it.reload().addOnCompleteListener { it2 ->
                    run {
                        if (it2.isSuccessful)
                            if (it.isEmailVerified) {
                                navigate()
                                _waitVerification.value = false
                            } else Toast.makeText(context, "not verified", Toast.LENGTH_LONG)
                    }
                }
            }
        }
    }

    fun verifiedOnClickCancel(navigate: () -> Unit) {
        navigate()
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCurrentUserFromAuth()?.addOnCompleteListener {
                if (it.isSuccessful) Log.d("AAA", "deleteCurrentUserFromAuth: success")
                else Log.d("AAA", "deleteCurrentUserFromAuth: ${it.exception?.message}")
            }
            repository.deleteUserFromBase(repository.getCurrentUser()!!.uid).addOnCompleteListener {
                if (it.isSuccessful) Log.d("AAA", "deleteUserFromBase: success")
                else Log.d("AAA", "deleteUserFromBase: ${it.exception?.message}")
            }
        }
        _waitVerification.value = false
    }

}
