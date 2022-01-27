package com.example.prep2.listeners

import com.example.prep2.model.PackageInfo

interface PackageSeletedListener {
    fun update(name: PackageInfo)
}