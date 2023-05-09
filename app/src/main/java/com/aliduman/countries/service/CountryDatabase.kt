package com.aliduman.countries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aliduman.countries.model.Country

@Database(entities = [Country::class], version = 1)//veritabanında değişiklik vs yaparsanız, versiyonu değiştirmeniz gerekir.
abstract class CountryDatabase : RoomDatabase(){

    abstract fun countryDao() : CountryDAO

    //Singleton

    companion object {//veritabanımızından 1den fazla obje oluşturulsun istemiyoruz. olursa çakışma olur.

        @Volatile private var instance : CountryDatabase? = null //Volatile -> farklı threadlerde görünür hale gelmesi için yazıyoruz.

        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock) {//sync-> aynı anda iki thread gelip çalışamıyor.
            instance ?: makeDatabase(context).also {
                instance = it
            }

        }

        private fun makeDatabase(context : Context) = Room.databaseBuilder(context.applicationContext,CountryDatabase::class.java,"countrydatabase").build()
    }

}