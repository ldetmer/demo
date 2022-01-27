package com.example.prep2

import android.app.Application
import com.example.prep2.di.AppComponent
//import com.example.prep2.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

import javax.inject.Inject

@HiltAndroidApp
class PrepApp : Application()