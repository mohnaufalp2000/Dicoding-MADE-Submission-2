package com.naufal.techmedia.ui.detail

import androidx.lifecycle.ViewModel
import com.naufal.techmedia.core.domain.model.News
import com.naufal.techmedia.core.domain.usecase.NewsUseCase

class DetailViewModel(private val mNewsUseCase: NewsUseCase): ViewModel() {
    fun setFavoriteNews(news: News, newStatus: Boolean, date: String) =
        mNewsUseCase.setFavoriteNews(news, newStatus, date)
}