package com.example.adtapp.model.network

import com.example.adtapp.model.data.RickyMortyResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface GetDataService{

    @GET("/api/character/")
    fun getCharacters(): Observable<RickyMortyResponse>

}