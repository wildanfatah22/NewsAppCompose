package com.example.newsappcompose.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.movieapps.presentation.utils.Dimens.MediumPadding1
import com.example.movieapps.presentation.utils.Dimens.SmallPadding2
import com.example.newsappcompose.domain.model.Article

@Composable
fun Articles(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val pagingHandle = pagingHandle(article = articles)

    if (pagingHandle) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = SmallPadding2)
        ) {
            items(
                count = articles.itemCount,
            ) {
                articles[it]?.let { article ->
                    CardArticle(article = article, onClick = {onClick(article)})
                }
            }
        }
    }
}

@Composable
fun pagingHandle(
    article: LazyPagingItems<Article>,
) : Boolean {
    val loadState = article.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)){
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}