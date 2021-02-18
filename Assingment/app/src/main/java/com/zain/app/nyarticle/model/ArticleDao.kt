package com.zain.app.nyarticle.model

import androidx.room.*
import com.zain.app.nyarticle.model.articleDataHolder.Articles


@Dao
interface ArticleDao {
    @get:Query("SELECT * FROM articles")
    val all: List<Articles>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg article: Articles)

    @Query("DELETE FROM articles")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticles(articleEntities: List<Articles?>?)

}