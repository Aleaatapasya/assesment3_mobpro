package com.aleaatapasya0002.assesment3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleaatapasya0002.assesment3.model.Cake
import com.aleaatapasya0002.assesment3.network.CakeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Cake>())
    private set

    init {
        retrieveData()
    }
    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
               data.value = CakeApi.service.getCake()
            } catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}