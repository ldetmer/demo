package com.example.prep2.model

import com.google.gson.annotations.SerializedName

data class ApkInfo(
    @SerializedName("applications")
    val applications: List<PackageInfo>
)




