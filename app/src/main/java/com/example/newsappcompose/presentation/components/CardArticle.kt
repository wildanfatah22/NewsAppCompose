package com.example.newsappcompose.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapps.presentation.utils.Dimens.CardSize
import com.example.movieapps.presentation.utils.Dimens.SmallPadding1
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.model.Source
import com.example.newsappcompose.presentation.ui.theme.NewsAppComposeTheme

@Composable
fun CardArticle(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() },

        ) {
        AsyncImage(
            modifier = Modifier
                .size(CardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(SmallPadding1))
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = SmallPadding1)
                .height(CardSize)
        ) {
            Text(
                text = article.publishedAt,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(SmallPadding1))
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = MaterialTheme.colorScheme.primary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsAppComposeTheme(dynamicColor = false) {
        CardArticle(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "Lorem"),
                title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                url = "",
                urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
            )
        )
    }
}