package com.zain.app.nyarticle.view.viewModel

import androidx.lifecycle.MutableLiveData
import com.zain.app.nyarticle.view.base.CoreViewModel
import com.zain.app.nyarticle.model.articleDataHolder.Articles
import com.zain.app.nyarticle.model.articleDataHolder.Media
import java.text.SimpleDateFormat
import java.util.*

class ArticleViewModel : CoreViewModel() {
    //main headline, image, snippet and publication date
    private val title: MutableLiveData<String> = MutableLiveData()
    private val image: MutableLiveData<String> = MutableLiveData()
    private val snippet: MutableLiveData<String> = MutableLiveData()
    private val publicationDate: MutableLiveData<String> = MutableLiveData()
    private val dateParser: SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    private val dateFormatter: SimpleDateFormat =
        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    fun bind(article: Articles/*, picasso: Picasso*/) {
//        title.value = article.headline!!.main
//        // TODO: check if it can be null???
//        var url: Multimedia? =
//            article.multimedia!!
//                .filter {
//                    it.type == "image" && it.subType == "thumbnail" && it.cropName == "thumbLarge"
//                }.firstOrNull()
//
//        if (url == null && article.multimedia.isNotEmpty()) {
//            url = article.multimedia
//                .filter {
//                    it.type == "image"
//                }.firstOrNull()
//        }
//
//        if (url != null) {
//            image.value =
//                if (url.url!!.contains(SITE_BASE_URL)) url.url else SITE_BASE_URL + url.url
//        }
//
//        snippet.value = article.leadParagraph ?: article.snippet
//
//        publicationDate.value = dateFormatter.format(dateParser.parse(article.pubDate)!!)
        title.value = article.title
        // TODO: check if it can be null???
        var media: Media? =
            article.media!!.filter { it.type == "image" && it.subtype == "photo" }.firstOrNull()

        if (media != null) {
            image.value = media.mediaMetadata.get(1).url

            snippet.value = article.abstractArticle
            publicationDate.value = article.published_date
        }
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getImage(): MutableLiveData<String> {
        return image
    }


    fun getSnippet(): MutableLiveData<String> {
        return snippet
    }

    fun getPublicationDate(): MutableLiveData<String> {
        return publicationDate
    }

}