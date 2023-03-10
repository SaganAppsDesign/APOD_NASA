package com.sagan.apod.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class APODModel (
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null,

    @SerializedName("date")
    @Expose
    var date: String? = null,

    @SerializedName("explanation")
    @Expose
    var explanation: String? = null,

    @SerializedName("hdurl")
    @Expose
    var hdurl: String? = null,

    @SerializedName("media_type")
    @Expose
    var mediaType: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("thumbnail_url")
    @Expose
    var thumbnail_url: String? = null
)