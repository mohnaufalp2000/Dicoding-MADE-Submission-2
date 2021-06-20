package com.naufal.techmedia.core.data.source.local.room

import androidx.room.*
import com.naufal.techmedia.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews() : Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE isFavorite = 1 ORDER by timeClicked ASC")
    fun getFavoriteNews() : Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsEntity: List<NewsEntity>)

    @Update
    fun updateNews(newsEntity: NewsEntity)

}