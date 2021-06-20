package com.naufal.techmedia.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.naufal.techmedia.core.domain.usecase.NewsUseCase

class FavoriteViewModel(mNewsUseCase: NewsUseCase) : ViewModel() {
    val favoriteNews = mNewsUseCase.getFavoriteNews().asLiveData()
}