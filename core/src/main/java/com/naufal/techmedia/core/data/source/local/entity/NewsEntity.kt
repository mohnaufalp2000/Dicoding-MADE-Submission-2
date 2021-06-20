package com.naufal.techmedia.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "news")
data class NewsEntity(
    @ColumnInfo(name = "title")
    var title: String? = "",

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "url")
    var url: String = "",

    @ColumnInfo(name = "urlToImage")
    var urlToImage: String? = "",

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String? = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "timeClicked")
    var timeClicked: String? = ""

) : Parcelable