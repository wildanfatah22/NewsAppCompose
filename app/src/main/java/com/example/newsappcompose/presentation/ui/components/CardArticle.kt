package com.example.newsappcompose.presentation.ui.components

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
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CardArticle(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val dateFormatInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val dateFormatOutput = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    val formattedDate = try {
        val date = dateFormatInput.parse(article.publishedAt)
        date?.let { dateFormatOutput.format(it) }
    } catch (e: ParseException) {
        null
    }


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
            formattedDate?.let { date ->
                Text(
                    text = date,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
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
                publishedAt = "2023-11-01",
                source = Source(id = "", name = "Lorem"),
                title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                url = "",
                urlToImage = "https://akcdn.detik.net.id/visual/2023/07/05/serangan-terbesar-israel-ke-jenin-dalam-20-tahun-5_169.jpeg?w=650&q=90"
            )
        )
    }
}