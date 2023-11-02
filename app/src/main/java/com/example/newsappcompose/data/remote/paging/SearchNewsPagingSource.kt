package com.example.newsappcompose.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsappcompose.data.remote.retrofit.ApiService
import com.example.newsappcompose.domain.model.Article
import java.lang.Exception

class SearchNewsPagingSource(
    private val apiService: ApiService,
    private val searchQuery: String,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = apiService.searchNews(searchQuery = searchQuery, sources = sources, page = page)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

}