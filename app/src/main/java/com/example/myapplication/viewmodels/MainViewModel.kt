package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.logging.Logger

//DepositInput(viewModel)
//DepositMargin()
//DepositCommission()
//DepositFinalSum()

class MainViewModel : ViewModel() {

    private val _depositInput = MutableStateFlow(0)
    private val _depositMargin = MutableStateFlow(0)
    private val _depositCommission = MutableStateFlow(0)
    private val _depositFinalSum = MutableStateFlow(0)

    val depositInput = _depositInput.asSharedFlow()
    val depositMargin = _depositMargin.asStateFlow()
    val depositCommission = _depositCommission.asStateFlow()
    val depositFinalSum = _depositFinalSum.asStateFlow()

    init {
        viewModelScope.launch {
            // Calculate commission and emit it to `_depositCommission`
            combine(_depositInput, _depositMargin) { amount, margin ->
                (amount * margin) / 100
            }.collect { calculatedCommission ->
                _depositCommission.value = calculatedCommission
            }
        }
    }


    fun setDepositAmount(amount: Int) {
        _depositInput.value = amount
    }

    fun setDepositMargin(margin: Int) {
        _depositMargin.value = margin
    }

    fun getFinalSum() {
        viewModelScope.launch {
            combine(_depositInput, _depositCommission) { amount, margin ->
                Log.d("Gregory", "$amount , $margin")
                amount + margin
            }.collect { calculatedCommission ->
                _depositFinalSum.value = calculatedCommission
            }
        }
    }

}
