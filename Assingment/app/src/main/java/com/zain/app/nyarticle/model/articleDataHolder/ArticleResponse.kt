package com.zain.app.nyarticle.model.articleDataHolder

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class Articles(
    @field:PrimaryKey
    @SerializedName("id")
    val id: Long,

    @SerializedName("published_date")
    val published_date: String ,

    @SerializedName("title")
    val title: String ,

    @SerializedName("abstract")
    val abstractArticle: String ,

    @SerializedName("media")
    val media: List<Media>

)
{
    override fun toString(): String {
        return "Article(id='$id', headline=$title, pubDate='$published_date', snippet=$abstractArticle)"
    }
}

data class Media(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int ,
    @SerializedName("caption")
    val caption: String ,
    @SerializedName("copyright")
    val copyright: String ,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata> ,
    @SerializedName("subtype")
    val subtype: String ,
    @SerializedName("type")
    val type: String
)

data class MediaMetadata(
    @SerializedName("format")
    val format: String ,
    @SerializedName("height")
    val height: Int ,
    @SerializedName("url")
    val url: String ,
    @SerializedName("width")
    val width: Int
)
