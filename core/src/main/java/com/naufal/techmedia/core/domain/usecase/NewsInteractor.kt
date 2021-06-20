package com.naufal.techmedia.core.domain.usecase

import com.naufal.techmedia.core.domain.model.News
import com.naufal.techmedia.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository): NewsUseCase {

    override fun getAllNews() = newsRepository.getAllNews()

    override fun getFavoriteNews() = newsRepository.getFavoriteNews()

    override fun setFavoriteNews(news: News, state: Boolean, date: String) = newsRepository.setFavoriteNews(news, state)
}