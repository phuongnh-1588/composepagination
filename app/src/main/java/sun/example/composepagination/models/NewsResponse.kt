package sun.example.composepagination.models

import sun.example.composepagination.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)