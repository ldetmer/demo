package com.example.prep2.di

import android.content.Context
import androidx.room.Room
import com.example.prep2.PackageViewModel
import com.example.prep2.api.Api
import com.example.prep2.model.AppDatabase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.Provides
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): Api {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient =  OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
        val gsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitBuilder =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
        val retrofit = retrofitBuilder.baseUrl("https://version.pelotoncycle.com").build()
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun providesDB (@ApplicationContext applicationContext: Context) : AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }



    //@Provides
    //fun providesPackageViewModel(api: Api) = PackageViewModel(api)// = Temp(api)
}