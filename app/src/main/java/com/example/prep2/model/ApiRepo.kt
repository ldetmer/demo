package com.example.prep2.model

import android.util.Log
import com.example.prep2.listeners.RetrievePackagesListener
import com.example.prep2.api.Api
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

const val API_ENDPOINT_LATEST = "latest"

class ApiRepo @Inject constructor(val api: Api, val db: AppDatabase) {

    var retrievePackagesListener: RetrievePackagesListener? = null
    val TAG = ApiRepo::class.java.name

    suspend fun getPackages(): List<PackageInfo> {

        var packages = emptyList<PackageInfo>()

        withContext(Dispatchers.IO) {
            db.packageDao().deleteAll()
            api.getApiCall(API_ENDPOINT_LATEST)
                .repeatWhen { retryApiCall(it) }
                .subscribeOn(
                    Schedulers.single()
                ).subscribe({
                    db.packageDao().deleteAll()
                    db.packageDao().insertAll(it.applications)
                    retrievePackagesListener?.update(it.applications)

                }, {
                    Log.e(TAG, "error retrieving data from API retrieving from db instead" + it.message)
                    packages = db.packageDao().getAll()
                })
        }

        return packages
    }

    private fun retryApiCall(it: Observable<Any>) =
        it.flatMap { retryCount: Any ->
            Observable.timer(10, TimeUnit.SECONDS)
        }

    fun lookUpPackage(packageName: String): PackageInfo? =
        db.packageDao().lookupPackageInfoByName(packageName)


}