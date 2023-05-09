package com.aliduman.countries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aliduman.countries.model.Country

@Dao
interface CountryDAO {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries: Country) : List<Long>
    //suspend -> coroutine, pause & resume
    //vararg  -> sayısı tam belli olmayan bir objeyi, farklı sayılarda verebilmek için kullanırız. multiple country objects
    //List<Long> -> primary keys

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryID")
    suspend fun getCountry(countryID: Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()




}