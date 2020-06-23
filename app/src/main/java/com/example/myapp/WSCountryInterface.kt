package com.example.myapp

import retrofit2.Call
import retrofit2.http.GET


interface WSCountryInterface {
    @GET("total/dayone/country/united-states")
    fun get_usa() : Call<List<CountryObject>>

    @GET("dayone/country/brazil")
    fun get_brazil() : Call<List<CountryObject>>

    @GET("dayone/country/russia")
    fun get_russia() : Call<List<CountryObject>>

    @GET("dayone/country/india")
    fun get_india() : Call<List<CountryObject>>

    @GET("total/dayone/country/united-kingdom")
    fun get_united_kingdom() : Call<List<CountryObject>>

    @GET("dayone/country/spain")
    fun get_spain() : Call<List<CountryObject>>

    @GET("dayone/country/italy")
    fun get_italy() : Call<List<CountryObject>>

    @GET("dayone/country/peru")
    fun get_peru() : Call<List<CountryObject>>

    @GET("total/dayone/country/france")
    fun get_france() : Call<List<CountryObject>>

    @GET("dayone/country/iran")
    fun get_iran() : Call<List<CountryObject>>
}