package com.example.prep2.di

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
