package com.example.prep2.model

import com.example.prep2.UpdaterListener2
import com.example.prep2.api.Api
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiRepo @Inject constructor (val  api: Api, val db: AppDatabase) {

    var updaterListener: UpdaterListener2? = null
    var stop = false

     suspend fun getPackages() : List<String> {

         var test = emptyList<String >()//db.packageDao().getAll().map { it.name }

             withContext(Dispatchers.IO) {
                 db.packageDao().deleteAll()


                     api.getApiCall("latest")
                 .repeatWhen {retryApiCall(it)}
                 .subscribeOn(Schedulers.single()
                 ).subscribe({


                      db.packageDao().insertAll(it.applications.map { PackageName(it.name) })

                     test = it.applications.map { it.name }
                     println("updating listener")
                     updaterListener?.update(test)


                 }, {
                     println("error " + it.message)
                     test = db.packageDao().getAll().map { it.name }
                 })


             }

return test
    }

    private fun retryApiCall(it: Observable<Any>) =
        it.flatMap { retryCount: Any ->
            Observable.timer(10, TimeUnit.SECONDS)
        }



}