package sun.example.composepagination.service

import retrofit2.http.GET
import retrofit2.http.Query
import sun.example.composepagination.models.NewsResponse

interface NewsApiService {
    companion object {
        const val API_KEY = "20a925b49ed048b582df70987f95088a"
    }

    @GET("everything?q=apple&sortBy=popularity&apiKey=${API_KEY}&pageSize=20")
    suspend fun getNews(
        @Query("page") page: Int
    ): NewsResponse
}