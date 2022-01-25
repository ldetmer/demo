package com.example.prep2

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.prep2.api.Api
import com.example.prep2.model.ApiRepo
import com.example.prep2.model.ApkInfo
import com.example.prep2.model.AppDatabase
import com.example.prep2.model.PackageName
import com.example.prep2.ui.home.CustomAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import javax.inject.Inject
import javax.inject.Singleton



@HiltViewModel
class PackageViewModel @Inject constructor (val  apiRepo: ApiRepo): ViewModel(), UpdaterListener2 {

    val disposables = CompositeDisposable()


        // Create a LiveData with a String
        val currentPackages: MutableLiveData<List<String>> by lazy {

                MutableLiveData<List<String>>().also {
                    viewModelScope.launch {
                        getSomething()
                    }
                }

        }

    val currentSelectedPackage= MutableLiveData<String> ()

    fun setPackage(selectedPackage: String) {
        currentSelectedPackage.postValue(selectedPackage)
    }

   /* fun getCurrentPackages(): MutableLiveData<List<String>> {
        return currentPackages
    }
*/


       suspend fun getSomething() {

            //viewModelScope.launch {
           apiRepo.updaterListener = this
                 val result = apiRepo.getPackages()
                println("resulst " + result.size)
                currentPackages.postValue(result)
            //}
           // api.getApiCall("latest").subscribeOn(Schedulers.io()).subscribe({










        //    }, {println("error " + it.message)
          //      currentPackages.postValue(db.packageDao().getAll().map { it.name } )}).also {  disposables.add(it)}
        }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    override fun update(names: List<String>) {
        currentPackages.postValue(names)
    }




}

interface UpdaterListener {
    fun update(name:String)


}

interface UpdaterListener2 {
    fun update(names:List<String>)


}