package com.rogo.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int?,
    val img: String?,
    val title: String?,
    val overview: String?,
    val movieImg: String?,
    val isFavorite: Boolean?,
):Parcelable