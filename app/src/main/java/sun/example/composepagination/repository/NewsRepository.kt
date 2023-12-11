package sun.example.composepagination.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import sun.example.composepagination.models.NewsPagingSource
import sun.example.composepagination.service.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {
    fun getNews() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsApiService)
        }
    ).flow
}