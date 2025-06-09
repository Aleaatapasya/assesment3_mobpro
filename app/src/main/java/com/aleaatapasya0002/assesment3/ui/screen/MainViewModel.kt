package com.aleaatapasya0002.assesment3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleaatapasya0002.assesment3.model.Cake
import com.aleaatapasya0002.assesment3.network.CakeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Cake>())
    private set

    var status = MutableStateFlow(CakeApi.ApiStatus.LOADING)

    init {
        retrieveData()
    }
    fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO) {
            status.value = CakeApi.ApiStatus.LOADING
            try {
               data.value = CakeApi.service.getCake()
               status.value = CakeApi.ApiStatus.SUCCESS
            } catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = CakeApi.ApiStatus.FAILED
            }
        }
    }
}