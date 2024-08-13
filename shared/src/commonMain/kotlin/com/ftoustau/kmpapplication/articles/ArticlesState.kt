package com.ftoustau.kmpapplication.articles

data class ArticlesState (
    val articles: List<Article> = listOf() ,
    val loading: Boolean = false,
    val error: Error? = null
)