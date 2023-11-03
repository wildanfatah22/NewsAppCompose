package com.example.newsappcompose.presentation.ui.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.presentation.ui.components.DetailTopBar
import com.example.newsappcompose.presentation.utils.DataState


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DetailScreen(
    article: Article,
    event: (DetailEvent) -> Unit,
    sideEffect: DataState?,
    navigateUp: () -> Unit,
    isBookmarked: State<Boolean>
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when(sideEffect){
                is DataState.Toast ->{
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailEvent.RemoveSideEffect)
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()) {
        DetailTopBar(
            isBookmarked = isBookmarked.value,
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