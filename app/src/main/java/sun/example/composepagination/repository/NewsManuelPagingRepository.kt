package sun.example.composepagination.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import sun.example.composepagination.models.NewsResponse
import sun.example.composepagination.service.NewsApiService
import javax.inject.Inject

class NewsManuelPagingRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {
    suspend fun getNews(page: Int): Flow<NewsResponse> = flow {
        try {
            emit(newsApiService.getNews(page))
        } catch (error: Exception) {
            emit(NewsResponse(emptyList(), error.message ?: "", 0))
        }
    }.flowOn(Dispatchers.IO)
}