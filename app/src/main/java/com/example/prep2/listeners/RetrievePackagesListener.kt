package com.example.prep2.listeners

import com.example.prep2.model.PackageInfo

interface RetrievePackagesListener {
    fun update(names:List<PackageInfo>)


}