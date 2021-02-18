package com.zain.app.nyarticle.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zain.app.nyarticle.model.ArticleDao
import com.zain.app.nyarticle.model.articleDataHolder.Articles

@Database(entities = [Articles::class], version = 1)
@TypeConverters(MediaTypeConverters::class)
abstract class NYTDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
