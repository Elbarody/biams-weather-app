package com.elbarody.data.remote.model


import com.google.gson.annotations.SerializedName

data class Item0(
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
)