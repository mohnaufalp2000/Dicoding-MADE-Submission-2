package com.naufal.techmedia.di

import com.naufal.techmedia.core.domain.usecase.NewsInteractor
import com.naufal.techmedia.core.domain.usecase.NewsUseCase
import com.naufal.techmedia.ui.detail.DetailViewModel
import com.naufal.techmedia.ui.home.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}