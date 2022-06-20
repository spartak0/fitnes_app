package com.example.fitness.ui.reg

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.data.FirebaseRepositoryImpl
import com.example.domain.models.User
import com.example.fitness.R
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegViewModel @Inject constructor(
    private val repository: FirebaseRepositoryImpl,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _user = MutableStateFlow(User())
    val user = _user.asStateFlow()

    fun regUser(user: User) {
        repository.regUser(user) {
            if (it.isSuccessful) {
                val auth = FirebaseAuth.getInstance()
                auth.currentUser?.let { it1 ->
                    addUserInDatabase(user, it1.uid)
                    _user.value = user
                }
            } else Toast.makeText(
                context,
                context.getString(R.string.failedReg),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun addUserInDatabase(user: User, uid: String) {
        repository.addUserInDatabase(user, uid) {
            if (it.isSuccessful) Log.d("AAA", "addUserInDatabase: success")
            else Log.d("AAA", "addUserInDatabase: not success")
        }
    }

    fun firstValidationTest(
        firstname: String,
        lastname: String,
        email: String,
        password: String
    ): Pair<Boolean, String> {
        if (firstname.isEmpty()) return Pair(false, context.getString(R.string.emptyFirstname))
        if (lastname.isEmpty()) return Pair(false, context.getString(R.string.emptyLastname))
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return Pair(
            false,
            context.getString(R.string.provideValidEmail)
        )
        if (password.isEmpty()) return Pair(false, context.getString(R.string.emptyPassword))
        if (password.length < 6) return Pair(false, context.getString(R.string.minPasswordLength))
        return Pair(true, context.getString(R.string.ok))
    }

    fun secondValidationTest(
        gender: String,
        birthDay: String,
        weight: Int,
        height: Int
    ): Pair<Boolean, String> {
        if (gender.isEmpty()) return Pair(false, context.getString(R.string.chooseGender))
        if (birthDay.isEmpty()) return Pair(false, context.getString(R.string.writeValidBirthday))
        if (weight == 0) return Pair(false, context.getString(R.string.validWeight))
        if (height == 0) return Pair(false, context.getString(R.string.validHeight))
        return Pair(true, context.getString(R.string.ok))
    }
}