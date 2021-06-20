package com.naufal.techmedia.core.data.source.remote

import com.naufal.techmedia.core.data.source.remote.network.ApiResponse
import com.naufal.techmedia.core.data.source.remote.network.ApiService
import com.naufal.techmedia.core.data.source.remote.response.ArticlesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllNews() : Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = apiService.getListNews()
                val body = response.articles
                if (body.isNotEmpty()){
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}