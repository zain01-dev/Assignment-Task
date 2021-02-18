package com.zain.app.nyarticle.view.utils

import com.zain.app.nyarticle.model.api.NetworkModule
import com.zain.app.nyarticle.view.viewModel.ArticleListViewModel
import com.zain.app.nyarticle.view.viewModel.ArticleViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(articleListViewModel: ArticleListViewModel)

    fun inject(postViewModel: ArticleViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
