package com.example.fitness.ui.reg

import android.content.Context
import android.location.GnssNavigationMessage
import android.util.Log
import android.util.Patterns
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FirebaseRepositoryImpl
import com.example.domain.models.User
import com.example.fitness.R
import com.example.fitness.ui.Screen
import com.example.fitness.ui.main.navigate
import com.example.utils.Constant
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
class FirstRegViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _error = MutableStateFlow(Pair(false, context.getString(R.string.ok)))
    val error = _error.asStateFlow()


    fun validationTest(
        firstname: String,
        lastname: String,
        email: String,
        password: String
    ) {
        if (firstname.isEmpty()) _error.value =
            Pair(true, context.getString(R.string.emptyFirstname))
        else if (lastname.isEmpty()) _error.value =
            Pair(true, context.getString(R.string.emptyLastname))
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) _error.value = Pair(
            true,
            context.getString(R.string.provideValidEmail)
        )
        else if (password.isEmpty()) _error.value =
            Pair(true, context.getString(R.string.emptyPassword))
        else if (password.length < 6) _error.value =
            Pair(true, context.getString(R.string.minPasswordLength))
        else _error.value = Pair(false, context.getString(R.string.ok))
    }


    fun onErrorClick() {
        _error.value = Pair(false, context.getString(R.string.ok))
    }

    fun onClickNext(user: User, navigate:()->Unit) {
        viewModelScope.launch{
            validationTest(
                user.firstname,
                user.lastname,
                user.email,
                user.password
            )
            if (!_error.value.first) navigate()
        }
    }

}