package com.naufal.techmedia.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.naufal.techmedia.core.domain.usecase.NewsUseCase

class MainViewModel(mNewsUseCase: NewsUseCase) : ViewModel() {
    val news = mNewsUseCase.getAllNews().asLiveData()
}