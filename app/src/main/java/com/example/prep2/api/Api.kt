package com.example.prep2.api

import com.example.prep2.model.ApkInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path



const val option = "v3/latest"

interface Api {
    @GET("v3/{payload}")
    fun getApiCall(@Path(value="payload") payload:String): Observable<ApkInfo>
}