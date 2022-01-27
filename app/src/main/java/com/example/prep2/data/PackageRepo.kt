package com.example.prep2.data

import com.example.prep2.model.PackageInfo
import timber.log.Timber

import javax.inject.Inject

const val API_ENDPOINT_LATEST = "latest"

class PackageRepo @Inject constructor(val api: Api, val db: AppDatabase) {

    val TAG = PackageRepo::class.java.name

    var packages = emptyList<PackageInfo>()

    suspend fun getPackages(): List<PackageInfo> {

        db.packageDao().deleteAll()
        try {
            val response = api.getPackages(API_ENDPOINT_LATEST)
            packages = response.applications
            db.packageDao().deleteAll()
            db.packageDao().insertAll(packages)
        } catch (e: Exception) {
            Timber.e(TAG, "error retrieving data from API retrieving from db instead" + e.message)
            packages = db.packageDao().getAll()
        }
        return packages
    }

    fun lookUpPackage(packageName: String): PackageInfo? {
        var packageFound = packages?.firstOrNull{ it.name == packageName }
        if (packageFound == null) {
            Timber.d(TAG, "package not found in list of packages, lookup from DB")
            return db.packageDao().lookupPackageInfoByName(packageName)
        }
        return packageFound
    }

}