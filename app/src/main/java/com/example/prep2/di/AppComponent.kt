package com.example.prep2.di

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.prep2.MainActivity
import com.example.prep2.PackageViewModel
import com.example.prep2.PrepApp
import com.example.prep2.ui.dashboard.DashboardFragment
import com.example.prep2.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

/*@Singleton
@Component(
    modules = [

        NetworkModule::class]
)*/
interface AppComponent {

   /* @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }*/

    // Factory to create instances of the AppComponent
   /* @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }*/

   // fun inject(application: PrepApp)
   // fun inject(activity: MainActivity)
    //fun inject(fragment: HomeFragment)
   // fun inject(fragment: DashboardFragment)
   // fun inject(packageViewModel: PackageViewModel)
}
