package com.zain.app.nyarticle.view.viewModel

import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import com.zain.app.nyarticle.R
import com.zain.app.nyarticle.view.base.CoreViewModel
import com.zain.app.nyarticle.model.ArticleDao
import com.zain.app.nyarticle.model.articleDataHolder.Articles
import com.zain.app.nyarticle.model.api.ArticleApi
import com.zain.app.nyarticle.view.utils.EndlessRecyclerOnScrollListener
import com.zain.app.nyarticle.view.adapters.ArticleListAdapter
import com.zain.app.nyarticle.view.utils.utility
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ArticleListViewModel(private val articleDao: ArticleDao) : CoreViewModel() {

    @Inject
    lateinit var articleApi: ArticleApi

    val articleListAdapter: ArticleListAdapter = ArticleListAdapter()

    val onScrollListener = object :
        EndlessRecyclerOnScrollListener() {
        override fun onLoadMoreArticles() {
            paginationIndex++
           // loadArticlesByKeyword(true)
        }
    }

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    var searchKeyword: MutableLiveData<String> = MutableLiveData()

    var paginationIndex: Int = 0

    val errorClickListener =
        View.OnClickListener { loadArticlesByKeyword(true) }

    val onSearchListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(newText: String): Boolean {
            if (newText.length > 2) {
                paginationIndex = 0
                searchKeyword.value = newText
                loadArticlesByKeyword(false)
                try {
                    Thread.sleep(50)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return false
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            paginationIndex = 0
            searchKeyword.value = query
            loadArticlesByKeyword(false)
            return false
        }
    }

    private lateinit var subscription: Disposable

    init {
        loadArticlesByKeyword(true)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadArticlesByKeyword(isAddArticles: Boolean) {
        subscription = Observable
            .fromCallable { articleDao.all }
            .concatMap { dbArticleList ->
                loader(searchKeyword.value.orEmpty(), isAddArticles).concatMap { apiArticles ->
                    println("apiList.size: ${apiArticles.size}")
                    if (apiArticles.isEmpty()) {
                        Observable.just(dbArticleList)
                    } else {
                        Observable.just(apiArticles)
                    }
                }

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                loadingVisibility.value = View.VISIBLE
                errorMessage.value = null
            }
            .doOnTerminate {
                loadingVisibility.value = View.GONE
                onScrollListener.setLoading(false)
            }
            .subscribe(
                { result ->
                    println("result.size: ${result.size}")
                    if (isAddArticles)
                        onAddRetrieveArticleListSuccess(result)
                    else
                        onRetrieveArticleListSuccess(result)
                },
                { error ->
                    if (searchKeyword.value == null) {
                        loadingVisibility.value = View.GONE
                        onScrollListener.setLoading(false)
                    } else {
                        onRetrieveArticleListError(error)
                    }
                }
            )
    }

    private fun loader(searchKeyword: String, isAddArticles: Boolean): Observable<List<Articles>> {
        if (articleDao.all != null && articleDao.all.size > 0 )
            return  Observable.just(articleDao.all)
        else{
            return articleApi.getArticles("XycaY5jVXLA3HnQFE5e2IdR8sMJ6XSqM").concatMap { apiArticleList ->
                if (!isAddArticles) {
                    articleDao.removeAll();
                }
                articleDao.insertAll(*apiArticleList.response!!.toTypedArray())
                Observable.just(apiArticleList.response)
            }
        }
    }

    private fun onRetrieveArticleListSuccess(articleList: List<Articles>) {
        articleListAdapter.updateArticleList(articleList)
    }


    private fun onAddRetrieveArticleListSuccess(articleList: List<Articles>) {
        articleListAdapter.addArticles(articleList)
    }

    private fun onRetrieveArticleListError(error: Throwable) {
        errorMessage.value = R.string.article_error_code_message
        if (error is retrofit2.HttpException) {
            val code: Int = error.code()
            errorMessage.value = when (code) {
                400 -> R.string.article_error_code_400
                429 -> R.string.article_error_code_429
                else -> R.string.article_error_code_message
            }
        }
        error.printStackTrace()
    }
}