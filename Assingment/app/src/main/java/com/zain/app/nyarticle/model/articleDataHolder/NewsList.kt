package com.zain.app.nyarticle.model.articleDataHolder

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class NewsList(
    val status: String,
    val copyright: String,
    @Embedded
    @SerializedName("results")
    val response: List<Articles>
)
