package com.naufal.techmedia.core.data

import com.naufal.techmedia.core.data.source.local.LocalDataSource
import com.naufal.techmedia.core.data.source.remote.RemoteDataSource
import com.naufal.techmedia.core.data.source.remote.network.ApiResponse
import com.naufal.techmedia.core.data.source.remote.response.ArticlesItem
import com.naufal.techmedia.core.domain.model.News
import com.naufal.techmedia.core.domain.repository.INewsRepository
import com.naufal.techmedia.core.util.AppExecutors
import com.naufal.techmedia.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {

    override fun getAllNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>(){
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getLocalNews().map { DataMapper.entitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getAllNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.responseToEntity(data)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getFavoriteNews(): Flow<List<News>> {
        return localDataSource.getFavoriteNews().map { DataMapper.entitiesToDomain(it) }
    }

    override fun setFavoriteNews(news: News, state: Boolean) {
        val newsEntity = DataMapper.domainToEntity(news)
        appExecutors.diskIO().execute { localDataSource.setFavoriteNews(newsEntity, state) }
    }
}