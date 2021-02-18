package com.zain.app.nyarticle.view.base

import androidx.lifecycle.ViewModel
import com.zain.app.nyarticle.model.api.NetworkModule
import com.zain.app.nyarticle.view.utils.DaggerViewModelInjector
import com.zain.app.nyarticle.view.viewModel.ArticleListViewModel
import com.zain.app.nyarticle.view.viewModel.ArticleViewModel
import com.zain.app.nyarticle.view.utils.ViewModelInjector


abstract class CoreViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is ArticleListViewModel -> injector.inject(this)
            is ArticleViewModel -> injector.inject(this)
        }
    }
}
