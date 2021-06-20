package com.naufal.techmedia.core.domain.usecase

import com.naufal.techmedia.core.domain.model.News
import com.naufal.techmedia.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(): Flow<Resource<List<News>>>

    fun getFavoriteNews(): Flow<List<News>>

    fun setFavoriteNews(news: News, state: Boolean, date: String)
}
