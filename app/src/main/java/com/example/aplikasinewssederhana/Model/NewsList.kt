package com.example.aplikasinewssederhana

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "article")
@Parcelize
data class Article(
    @PrimaryKey(true)val id: Int?,
    val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String,
    val publishedAt: String?,
    val content: String?
) : Parcelable

@Entity(tableName = "source")
@Parcelize
data class Source(
    val id: String?,
    val name: String,
): Parcelable

@Entity(tableName = "news")
@Parcelize
data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>,
) : Parcelable

