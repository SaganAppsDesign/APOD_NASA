package com.example.reccyclerview

import com.google.gson.annotations.SerializedName

data class DogsResponse (

    //Para cambiar nombre de los campos de origen de la API
    @SerializedName("status") var status: String,
    @SerializedName("message") var images: List<String>

    )