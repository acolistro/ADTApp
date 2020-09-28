package com.example.adtapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.adtapp.model.RickyMortyDataRepository
import com.example.adtapp.model.data.RickyMortyResponse

class RickyMortyViewModel : ViewModel(){

    fun getCharacters(): LiveData<RickyMortyResponse> = RickyMortyDataRepository.getCharacters()

    override fun onCleared() {
        super.onCleared()
        RickyMortyDataRepository.clearDisposable()
    }
}