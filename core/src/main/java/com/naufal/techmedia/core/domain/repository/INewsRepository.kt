package com.naufal.techmedia.core.domain.repository

import com.naufal.techmedia.core.data.Resource
import com.naufal.techmedia.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getAllNews(): Flow<Resource<List<News>>>

    fun getFavoriteNews(): Flow<List<News>>

    fun setFavoriteNews(news: News, state: Boolean)

}