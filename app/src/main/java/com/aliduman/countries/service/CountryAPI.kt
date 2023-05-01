package com.aliduman.countries.service

import com.aliduman.countries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries() : Single<List<Country>>//bir defa işlemi yapar ve durur.
}