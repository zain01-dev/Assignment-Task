package com.zain.app.nyarticle.model.api

import com.zain.app.nyarticle.model.articleDataHolder.NewsList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("mostviewed/all-sections/30.json")
    fun getArticles(
        @Query("api-key") apiKey: String
    ): Observable<NewsList>

}