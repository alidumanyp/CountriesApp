package com.aliduman.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliduman.countries.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {
        val country = Country("Turkey","Ankara","Asia","Try","Turkish","www.ss.com")
        countryLiveData.value = country

    }



}