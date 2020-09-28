package com.example.adtapp.model.network

import com.example.adtapp.model.data.RickyMortyResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RickyRetrofitInstance {

    private val BASE_URL = "https://rickandmortyapi.com"

    private var getDataService: GetDataService

    init {
        getDataService = createService(createRetrofitInstance())
    }

    private fun createRetrofitInstance(): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private fun createService(retrofitInstance: Retrofit): GetDataService = retrofitInstance.create(GetDataService::class.java)

    fun getCharacters(): Observable<RickyMortyResponse> = getDataService.getCharacters()
}