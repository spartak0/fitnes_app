package com.example.fitness.ui.secondReg

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FirebaseRepositoryImpl
import com.example.domain.FirebaseRepository
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
    private val repository: FirebaseRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _error = MutableStateFlow(Pair(false, context.getString(R.string.ok)))
    val error = _error.asStateFlow()

    fun validationTest(
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
                        repository.getCurrentUser()?.let { it1 ->
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