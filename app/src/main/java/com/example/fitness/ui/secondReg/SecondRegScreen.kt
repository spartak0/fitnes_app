package com.example.fitness.ui.secondReg

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.domain.models.User
import com.example.fitness.R
import com.example.fitness.ui.Screen
import com.example.fitness.ui.details.EditText
import com.example.fitness.ui.details.Gradient
import com.example.fitness.ui.details.errorDialog
import com.example.fitness.ui.main.GradientView
import com.example.fitness.ui.secondReg.SecondRegViewModel
import com.example.fitness.ui.secondReg.UserAnthropometry
import com.example.fitness.ui.theme.Typography
import java.util.*

@Composable
fun SecondRegScreen(
    navController: NavController,
    user: User,
    viewModel: SecondRegViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val error by viewModel.error.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.girl_training),
            contentDescription = "",
            modifier = Modifier.height(250.dp)
        )
        Text(
            text = stringResource(R.string.completeProfile),
            modifier = Modifier.padding(top = 30.dp),
            style = Typography.body1,
            fontSize = 20.sp,
        )
        Text(
            text = stringResource(R.string.moreAboutYou),
            style = Typography.body2,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.size(30.dp))
        Gender(user)
        Spacer(modifier = Modifier.size(15.dp))
        Birthday(context = LocalContext.current, user)
        Spacer(modifier = Modifier.size(15.dp))
        Anthropometry(UserAnthropometry.Weight, user)
        Spacer(modifier = Modifier.size(15.dp))
        Anthropometry(UserAnthropometry.Height, user)
        Spacer(modifier = Modifier.size(15.dp))
        GradientView(
            text = stringResource(R.string.next),
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.view_height))
                .padding(horizontal = 30.dp)
                .clickable {
                    viewModel.validationTest(
                        user.gender,
                        user.birthDay,
                        user.weight,
                        user.height
                    )
                    if (!error.first) {
                        viewModel.regUser(user) {
                            navController.navigate(Screen.BottomBarScreen.route)
                        }
                    }
                },
            gradient = Gradient.blue
        )
    }
    if (error.first)
        errorDialog(text = error.second) {
            viewModel.onErrorClick()
        }
}


@Composable
fun Anthropometry(userAnthropometry: UserAnthropometry, user: User) {
    var value by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        EditText(
            modifier = Modifier.width(245.dp),
            value = value,
            onValueChange = {
                value = it
            },
            placeholderText = stringResource(id = userAnthropometry.hintId),
            keyboardType = KeyboardType.Decimal,
            leadingIcon = {
                Icon(
                    painterResource(id = userAnthropometry.iconId), ""
                )
            }
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(Gradient.pink)
                .size(dimensionResource(id = R.dimen.view_height))
        ) {
            Text(
                text = stringResource(id = userAnthropometry.unitId),
                style = Typography.body2,
                color = Color.White
            )
        }
        when (userAnthropometry) {
            UserAnthropometry.Weight -> {
                user.weight =
                    if (value.isEmpty() or !value.matches("[\\d]+".toRegex())) 0 else value.toInt()
            }
            UserAnthropometry.Height -> {
                user.height =
                    if (value.isEmpty() or !value.matches("[\\d]+".toRegex())) 0 else value.toInt()
            }
        }
    }
}


@Composable
fun Birthday(context: Context, user: User) {
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    var date by remember { mutableStateOf("") }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date = "${
                if (dayOfMonth / 10 == 0)
                    "0$dayOfMonth"
                else dayOfMonth
            }.${
                if (month / 10 == 0)
                    "0$month"
                else month
            }.$year"
        }, year, month, day
    )
    if (isPressed) {
        datePickerDialog.show()
    }
    EditText(
        value = date,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        onValueChange = {
        },
        placeholderText = stringResource(R.string.dateOfBirthday),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null
            )
        },
        readOnly = true,
        interactionSource = interactionSource
    )
    user.birthDay = date
}

@Composable
fun Gender(user: User) {
    var expanded by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val list = listOf(stringResource(R.string.male), stringResource(R.string.female))
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    if (isPressed) {
        expanded = !expanded
    }

    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
        EditText(
            value = gender,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },

            onValueChange = {
                gender = it
            },
            placeholderText = stringResource(id = R.string.chooseGender),
            trailingIcon = {
                Icon(icon, "", modifier = Modifier
                    .clickable { expanded = !expanded })
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_2user),
                    contentDescription = null
                )
            },
            readOnly = true,
            interactionSource = interactionSource
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(onClick = {
                    gender = label
                    expanded = false
                }) {
                    Text(text = label)
                }
                if (list.last() != label) Divider()
            }

        }
    }
    user.gender = gender
}
