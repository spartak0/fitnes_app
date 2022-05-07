package com.example.fitness.ui.reg

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.fitness.R
import com.example.fitness.ui.details.EditText
import com.example.fitness.ui.details.Gradient
import com.example.fitness.ui.main.GradientView
import com.example.fitness.ui.theme.Typography
import java.util.*

@Composable
fun SecondRegScreen(navController: NavController) {
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
            text = "Let's complete your profile",
            modifier = Modifier.padding(top = 30.dp),
            style = Typography.body1,
            fontSize = 20.sp,
        )
        Text(
            text = "It will help us to know more about you!",
            style = Typography.body2,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.size(30.dp))
        DropDownMenu()
        Spacer(modifier = Modifier.size(15.dp))
        Birthday(context = LocalContext.current)
        Spacer(modifier = Modifier.size(15.dp))
        WeightOrHeightRow(R.drawable.ic_weight, "Your Weight", "KG")
        Spacer(modifier = Modifier.size(15.dp))
        WeightOrHeightRow(R.drawable.ic_height, "Your Height", "CM")
        Spacer(modifier = Modifier.size(15.dp))
        GradientView(
            text = "Next",
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 30.dp)
                .clickable { },
            gradient = Gradient.blue
        )

    }
}

@Composable
fun WeightOrHeightRow(leadIcon: Int, hint: String, boxText: String) {
    var value by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        EditText(
            modifier = Modifier.width(245.dp),
            value = value,
            onValueChange = { value = it },
            placeholderText = hint,
            keyboardType = KeyboardType.Decimal,
            leadingIcon = {
                Icon(
                    painterResource(id = leadIcon), ""
                )
            }
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(Gradient.pink)
                .size(55.dp)
        ) {
            Text(text = "$boxText", style = Typography.body2, color = Color.White)
        }
    }
}

@Composable
fun Birthday(context: Context) {
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
            date = it
        },
        placeholderText = "Date of Birth",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null
            )
        },
        readOnly = true,
        interactionSource = interactionSource
    )

}

@Composable
fun DropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val list = listOf("Male", "Female")
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
            placeholderText = "Choose gender",
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

}
