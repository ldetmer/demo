package com.example.prep2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prep2.listeners.RetrievePackagesListener
import com.example.prep2.model.ApiRepo
import com.example.prep2.model.PackageInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PackageViewModel @Inject constructor (val  apiRepo: ApiRepo): ViewModel(), RetrievePackagesListener {

    val disposables = CompositeDisposable()


        // Create a LiveData with a String
        val currentPackages: MutableLiveData<List<PackageInfo>> by lazy {

                MutableLiveData<List<PackageInfo>>().also {
                    viewModelScope.launch {
                        getSomething()
                    }
                }

        }

    val currentSelectedPackage= MutableLiveData<PackageInfo> ()

    fun setPackage(selectedPackage: PackageInfo) {
        currentSelectedPackage.postValue(selectedPackage)
    }

    fun setPackage (packageName: String) {
        apiRepo.lookUpPackage(packageName)?.let {
            setPackage(it)
        }
    }


       suspend fun getSomething() {

            //viewModelScope.launch {
           apiRepo.retrievePackagesListener = this
                 val result = apiRepo.getPackages()

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

    override fun update(names: List<PackageInfo>) {
        currentPackages.postValue(names)
    }




}

