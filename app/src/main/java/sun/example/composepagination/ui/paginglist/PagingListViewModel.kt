package sun.example.composepagination.ui.paginglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import sun.example.composepagination.models.Article
import sun.example.composepagination.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class PagingListViewModel @Inject constructor(
    private val repository: NewsRepository,
): ViewModel() {

    fun getBreakingNews(): Flow<PagingData<Article>> = repository.getNews().cachedIn(viewModelScope)
}