package com.aliduman.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliduman.countries.model.Country
import com.aliduman.countries.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()

    var countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()


    fun refreshData() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        countryLoading.value = true

        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        countries.value = t
                        countryError.value = false
                        countryLoading.value = false //çünkü yüklenme bitti.
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }

                })

        )

    }


}