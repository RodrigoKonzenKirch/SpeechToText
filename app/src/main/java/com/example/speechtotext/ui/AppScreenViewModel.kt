package com.example.speechtotext.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppScreenViewModel @Inject constructor() : ViewModel() {
    private val _outputText = mutableStateOf("")
    var outputText: State<String> = _outputText
        private set

    fun setOutputText(value: String){
        _outputText.value = value
    }


}