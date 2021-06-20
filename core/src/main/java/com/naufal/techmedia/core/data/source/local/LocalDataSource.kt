package com.naufal.techmedia.core.data.source.local

import com.naufal.techmedia.core.data.source.local.entity.NewsEntity
import com.naufal.techmedia.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val newsDao: NewsDao) {

    fun getLocalNews(): Flow<List<NewsEntity>> = newsDao.getAllNews()

    fun getFavoriteNews(): Flow<List<NewsEntity>> = newsDao.getFavoriteNews()

    suspend fun insertNews(newsList: List<NewsEntity>) = newsDao.insertNews(newsList)

    fun setFavoriteNews(news: NewsEntity, newState: Boolean){
        news.isFavorite = newState
        newsDao.updateNews(news)
    }

}