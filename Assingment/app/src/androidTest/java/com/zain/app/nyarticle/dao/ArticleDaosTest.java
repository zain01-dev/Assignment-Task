package com.zain.app.nyarticle.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.zain.app.nyarticle.model.articleDataHolder.Articles;
import com.zain.app.nyarticle.model.database.NYTDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class ArticleDaosTest {

    private NYTDatabase articleDatabase;

    @Before
    public void init(){
        articleDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), NYTDatabase.class).build();
    }

    @After
    public void uninit(){
        articleDatabase.close();
    }
    @Test
    public void testLoadPopularArticles(){
        List<Articles> articleEntities = new ArrayList<>();
        Articles entity = new Articles(1000,"test","test","test",null);
        articleEntities.add(entity);
        articleDatabase.articleDao().saveArticles(articleEntities);
        LiveData<List<Articles>> articlesList = (LiveData<List<Articles>>) articleDatabase.articleDao().getAll();
        assertNotNull(articlesList);
    }

}
