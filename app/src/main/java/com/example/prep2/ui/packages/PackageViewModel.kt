package com.example.prep2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prep2.data.PackageRepo
import com.example.prep2.model.PackageInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PackageViewModel @Inject constructor(val packageRepo: PackageRepo) : ViewModel() {

    // live model observer to listen for package selected
    val currentSelectedPackage : MutableLiveData<PackageInfo> by lazy {
        MutableLiveData<PackageInfo>()
    }

    // live model observer to listen for list of package updates
    val currentPackages: MutableLiveData<List<PackageInfo>> by lazy {
        MutableLiveData<List<PackageInfo>>()
    }

    //async functional call to get packages and post to live model when receives response from repo
    //show how live model works with I/O call response
    fun getPackages() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                currentPackages.postValue(packageRepo.getPackages())
            }
        }

    }

    fun setPackage(selectedPackage: PackageInfo) {
        currentSelectedPackage.postValue(selectedPackage)
    }

    //async functional call to lookup package from DB, potentially if offline and fragment is resumed
    fun setPackage(packageName: String) {
        packageRepo.lookUpPackage(packageName)?.let {
            setPackage(it)
        }
    }

}

