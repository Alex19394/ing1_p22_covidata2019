package com.example.myapp

import retrofit2.Call
import retrofit2.http.GET

interface WSWorldInterface {
    @GET("world")
    fun getallworld() : Call<List<WorldObject>>

    @GET("world/total")
    fun getworldtotal() : Call<WorldObject>
}