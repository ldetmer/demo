package com.example.prep2

import android.app.Application
import com.example.prep2.di.AppComponent
//import com.example.prep2.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

import javax.inject.Inject

@HiltAndroidApp
class PrepApp : Application() {


     /*private val appComponent: AppComponent by lazy {
       DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }*/

    // Instance of the AppComponent that will be used by all the Activities in the project
   /* val appComponent: AppComponent by lazy {
        initializeComponent()
    }*/





   // @Inject
    //lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

   // override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

}