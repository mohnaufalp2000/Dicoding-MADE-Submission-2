package com.naufal.techmedia.core.util

import com.naufal.techmedia.core.data.source.local.entity.NewsEntity
import com.naufal.techmedia.core.data.source.remote.response.ArticlesItem
import com.naufal.techmedia.core.domain.model.News

object DataMapper {

    fun responseToEntity(input: List<ArticlesItem>) : List<NewsEntity>{
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                title = it.title,
                url = it.url,
                urlToImage = if (it.urlToImage == null) "null" else it.urlToImage.toString(),
                publishedAt = it.publishedAt,
                isFavorite = false,
                timeClicked = ""
            )
            newsList.add(news)
        }
        return newsList
    }

    fun entitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                title = it.title,
                url = it.url,
                urlToImage = if (it.urlToImage == null) "null" else it.urlToImage,
                publishedAt = it.publishedAt,
                isFavorite = it.isFavorite,
                timeClicked = it.timeClicked
            )
        }

    fun domainToEntity(input: News) = NewsEntity(
        title = input.title,
        url = input.url,
        urlToImage = input.urlToImage,
        publishedAt = input.publishedAt,
        isFavorite = input.isFavorite,
        timeClicked = input.timeClicked
    )

}