package com.example.newsappcompose.presentation.ui.detail

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.presentation.ui.components.DetailTopBar


@Composable
fun DetailScreen(
    article: Article,
    event: (DetailEvent) -> Unit,
    navigateUp: () -> Unit,

) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()) {
        DetailTopBar(
            onBookMarkClick = {
                event(DetailEvent.UpsertDeleteArticle(article))
            },
            onBackClick = navigateUp
        )

        var webView: WebView? = null

        AndroidView(
            modifier = Modifier,
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    webViewClient = object : WebViewClient(){
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                        }
                    }
                    settings.javaScriptEnabled = true
                    loadUrl(article.url)
                    webView = this
                }
            }, update = {
                webView = it
            }
        )
    }
}