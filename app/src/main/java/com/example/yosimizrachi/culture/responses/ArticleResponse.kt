package com.example.yosimizrachi.culture.responses


data class ArticleResponse constructor(val data: List<Article>) {

    data class Article constructor(val metaData: MetaData,
                                   val id: String,
                                   val title: String,
                                   val imageUrl: String,
                                   val isSaved: Boolean,
                                   val isLiked: Boolean,
                                   val likesCount: Int,
                                   val category: String,
                                   val author: Author)

    data class MetaData constructor(val creationTime: String, val updateTime: String)

    data class Author constructor(val id: Int, val authorName: String, val authorAvatar: AuthorAvatar)

    data class AuthorAvatar constructor(val imageUrl: String)
}

