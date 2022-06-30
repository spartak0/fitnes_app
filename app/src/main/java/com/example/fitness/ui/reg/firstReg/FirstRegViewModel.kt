package com.example.fitness.ui.reg.firstReg

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.User
import com.example.fitness.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstRegViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _error = MutableStateFlow(Pair(false, context.getString(R.string.ok)))
    val error = _error.asStateFlow()


    private fun validationTest(
        user: User
    ) {
        if (user.firstname.isEmpty()) _error.value =
            Pair(true, context.getString(R.string.emptyFirstname))
        else if (user.lastname.isEmpty()) _error.value =
            Pair(true, context.getString(R.string.emptyLastname))
        else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) _error.value = Pair(
            true,
            context.getString(R.string.provideValidEmail)
        )
        else if (user.password.isEmpty()) _error.value =
            Pair(true, context.getString(R.string.emptyPassword))
        else if (user.password.length < 6) _error.value =
            Pair(true, context.getString(R.string.minPasswordLength))
        else _error.value = Pair(false, context.getString(R.string.ok))
    }


    fun onErrorClick() {
        _error.value = Pair(false, context.getString(R.string.ok))
    }

    fun onClickNext(user: User, navigate: () -> Unit) {
        viewModelScope.launch {
            validationTest(
                user
            )
            if (!_error.value.first) navigate()
        }
    }

}