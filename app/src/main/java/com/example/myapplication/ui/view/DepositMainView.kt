package com.example.myapplication.ui.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodels.MainViewModel

@Preview
@Composable
fun MainScreenPreview() {

    MyApplicationTheme {
        DepositValues(MainViewModel())
    }
}

@Composable
fun DepositValues(viewModel: MainViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DepositInput(viewModel)
        DepositMargin(viewModel)
        DepositCommission(viewModel)
        DepositFinalSum(viewModel)
    }

    FooterBranding(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 40.dp),
        "\uD83C\uDDF2\uD83C\uDDE9"
    )
}

@Composable
fun DepositInput(viewModel: MainViewModel) {

    val text by viewModel.depositInput.collectAsState(0)

    Row(
        modifier = Modifier.fillMaxWidth(1f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.37f),
            text = "Deposit",
            style = MaterialTheme.typography.titleLarge
        )
        TextField(
            modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth(0.9f),
            value = text.toString(),
            onValueChange = {
                if (it.isEmpty() || it.matches(Regex("^\\d+\$"))) {
                    viewModel.setDepositAmount(it.toInt())
                }
            },

            textStyle = MaterialTheme.typography.titleLarge,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun DepositMargin(viewModel: MainViewModel) {

    val margin = viewModel.depositMargin.collectAsState(initial = 0).value


    Row(
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.37f)
                .padding(top = 20.dp),
            text = "$margin%",
            style = MaterialTheme.typography.titleLarge
        )
        Column {
            Slider(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 20.dp),
                value = viewModel.depositMargin.collectAsState(0).value.toFloat(),
                onValueChange = { viewModel.setDepositMargin(it.toInt()) },
                valueRange = 0f..100f,
                steps = 100
            )
            Text(text = viewModel.depositMargin.collectAsState(0).value.toString())
        }
    }


}

@Composable
fun DepositCommission(viewModel: MainViewModel) {
    // Collect depositCommission instead of depositMargin
    val commission = viewModel.depositCommission.collectAsState(initial = 0).value

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.37f)
                .padding(top = 20.dp),
            text = "Commission",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 20.dp),
            text = commission.toString(),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun DepositFinalSum(viewModel: MainViewModel) {

    viewModel.getFinalSum()
    val finalSum = viewModel.depositFinalSum.collectAsState(initial = 0).value

    Row(
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.37f)
                .padding(top = 20.dp),
            text = "Sum",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 20.dp),
            text = finalSum.toString(),
            style = MaterialTheme.typography.titleLarge
        )

    }
}