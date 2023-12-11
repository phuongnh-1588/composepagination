package sun.example.composepagination.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import sun.example.composepagination.repository.NewsManuelPagingRepository
import sun.example.composepagination.repository.NewsRepository
import sun.example.composepagination.service.NewsApiService

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideNewsRepository(newsApiService: NewsApiService): NewsRepository = NewsRepository(newsApiService)

    @Provides
    fun provideNewsManuelPagingRepository(newsApiService: NewsApiService): NewsManuelPagingRepository = NewsManuelPagingRepository(newsApiService)
}