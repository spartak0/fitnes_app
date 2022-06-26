package com.example.fitness.ui.secondReg

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FirebaseRepositoryImpl
import com.example.domain.models.User
import com.example.fitness.R
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SecondRegViewModel @Inject constructor(
    private val repository: FirebaseRepositoryImpl,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _error = MutableStateFlow(Pair(false, context.getString(R.string.ok)))
    val error = _error.asStateFlow()
//
//    fun regUser(user: User, navigate: () -> Unit) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.regUser(user.email, user.password).addOnCompleteListener {
//                //что внутри листнера вызывается в мэйн треде
//                viewModelScope.launch(Dispatchers.IO) {
//                    if (it.isSuccessful) {
//                        val auth = FirebaseAuth.getInstance()
//                        auth.currentUser?.let { it1 ->
//                            addUserInDatabase(user, it1.uid)
//                        }
//                    } else _error.value = Pair(true, context.getString(R.string.failedReg))
//                }
//            }
//        }
//    }


    fun validationTest(
        gender: String,
        birthDay: String,
        weight: Int,
        height: Int
    ) {
        if (gender.isEmpty())
            _error.value =
                Pair(
                    true,
                    context.getString(R.string.chooseGender)
                )
        else if (birthDay.isEmpty()) _error.value =
            Pair(true, context.getString(R.string.writeValidBirthday))
        else if (weight == 0) _error.value = Pair(true, context.getString(R.string.validWeight))
        else if (height == 0) _error.value = Pair(true, context.getString(R.string.validHeight))
        else _error.value = Pair(false, context.getString(R.string.ok))
    }

    fun onErrorClick() {
        _error.value = Pair(false, context.getString(R.string.ok))
    }

    fun regUser(user: User, navigate: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            validationTest(user.gender, user.birthDay, user.weight, user.height)
            if (!_error.value.first) {
                repository.regUser(user.email, user.password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val auth = FirebaseAuth.getInstance()
                        auth.currentUser?.let { it1 ->
                            viewModelScope.launch(Dispatchers.IO) {
                                addUserInDatabase(user, it1.uid)
                            }
                        }
                        navigate()
                    } else _error.value = Pair(true, context.getString(R.string.failedReg))
                }
            }
        }
    }

    private fun addUserInDatabase(user: User, uid: String) {
        repository.addUserInDatabase(user, uid).addOnCompleteListener {
            if (it.isSuccessful) Log.d("AAA", "addUserInDatabase: success")
            else Log.d("AAA", "addUserInDatabase: not success")
        }
    }

}