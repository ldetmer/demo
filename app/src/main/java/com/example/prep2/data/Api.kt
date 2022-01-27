package com.example.prep2.data

import com.example.prep2.model.ApkInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("v3/{payload}")
    suspend fun getPackages(@Path(value="payload") payload:String): ApkInfo
}