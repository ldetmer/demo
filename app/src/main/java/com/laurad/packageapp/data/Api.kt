package com.laurad.packageapp.data

import com.laurad.packageapp.model.ApkInfo
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("v3/{payload}")
    suspend fun getPackages(@Path(value="payload") payload:String): ApkInfo
}