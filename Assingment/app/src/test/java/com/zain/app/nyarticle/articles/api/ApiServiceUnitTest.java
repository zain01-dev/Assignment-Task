package com.zain.app.nyarticle.articles.api;
import com.zain.app.nyarticle.model.api.ArticleApi;
import com.zain.app.nyarticle.model.articleDataHolder.NewsList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ApiServiceUnitTest {

   private Retrofit apiService;
     ArticleApi articleApi;
    TestSubscriber<NewsList> mSubscriber;
    @Before
    public void createService() {

        apiService = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        articleApi = apiService.create(ArticleApi.class);
        mSubscriber = new TestSubscriber<>();
    }


    @Test
    public void getPopularArticles() {
        try {
            articleApi.getArticles("XycaY5jVXLA3HnQFE5e2IdR8sMJ6XSqM").subscribe((Observer<? super NewsList>) mSubscriber);
            mSubscriber.assertNoErrors();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
