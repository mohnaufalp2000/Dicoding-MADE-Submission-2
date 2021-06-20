package com.naufal.techmedia.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    var title: String? = "",

    var url: String = "",

    var urlToImage: String? = "",

    var publishedAt: String? = "",

    var isFavorite: Boolean = false,

    var timeClicked: String? = ""
) : Parcelable