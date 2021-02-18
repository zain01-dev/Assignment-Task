package com.zain.app.nyarticle.articles.entity;

import com.zain.app.nyarticle.model.ArticleDao;
import com.zain.app.nyarticle.model.articleDataHolder.Articles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class EntityUnitTest {

    @Test
    public void testId(){
        Articles articleEntity = new Articles(1000,"","","",null);
        assertEquals(articleEntity.getId(), 1000);
    }

    @Test
    public void testTitle(){
        Articles articleEntity = new Articles(1000,"","test","",null);
        assertEquals(articleEntity.getTitle(), "test");
    }

    @Test
    public void testPublishDate(){
        Articles articleEntity = new Articles(1000,"test","test","",null);
        assertEquals(articleEntity.getPublished_date(), "test");
    }

    @Test
    public void testAuthors(){
        Articles articleEntity = new Articles(1000,"test","test","test",null);
        assertEquals(articleEntity.getAbstractArticle(), "test");
    }
}
