package com.example.adtapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.adtapp.model.data.RickyMortyResponse
import com.example.adtapp.model.network.RickyRetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

object RickyMortyDataRepository{
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val characterLiveData: MutableLiveData<RickyMortyResponse> = MutableLiveData()

    fun getCharacters(): LiveData<RickyMortyResponse> {

        compositeDisposable.add(
            RickyRetrofitInstance.getCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({data ->
                    characterLiveData.value = data
                }, {throwable ->
                    Log.e("TAG_ERROR", "${throwable.toString()}")
                })
        )

        return characterLiveData
    }

    fun clearDisposable(){
        compositeDisposable.clear()
    }


}