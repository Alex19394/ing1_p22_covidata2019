package com.example.myapp

data class CountryObject(val Country: String,
                         val CountryCode: String,
                         val Province: String,
                         val CityCode:String,
                         val Lat: String,
                         val Lon: String,
                         val Confirmed: Int,
                         val Deaths: Int,
                         val Recovered: Int,
                         val Active: Int,
                         val Date: String)