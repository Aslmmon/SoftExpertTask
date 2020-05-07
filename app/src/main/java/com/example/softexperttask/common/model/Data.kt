package com.example.softexperttask.common.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("brand")
    var brand: String?,
    @SerializedName("constructionYear")
    var constructionYear: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("imageUrl")
    var imageUrl: String?,
    @SerializedName("isUsed")
    var isUsed: Boolean?
)