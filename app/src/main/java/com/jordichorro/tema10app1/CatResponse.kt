package com.jordichorro.tema10app1

import com.google.gson.annotations.SerializedName

data class CatResponse(@SerializedName ("data") val data: List<String>, @SerializedName ("id") val id: Int)
