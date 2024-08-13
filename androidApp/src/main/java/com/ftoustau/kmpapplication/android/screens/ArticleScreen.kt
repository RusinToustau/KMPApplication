package com.ftoustau.kmpapplication.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ftoustau.kmpapplication.articles.Article
import com.ftoustau.kmpapplication.articles.ArticlesViewModel


@Composable
fun ArticleScreen(
    onAboutButtonClick: () -> Unit,
    articlesViewModel: ArticlesViewModel
) {

    val articleState = articlesViewModel.articlesState.collectAsState()

    Column {
        AppBar(onAboutButtonClick)
        if (articleState.value.loading)
            Loader()
        if (articleState.value.error != null)
            articleState.value.error?.message?.let { ErrorMessage(it) }
        if (articleState.value.articles.isNotEmpty())
            ArticleListView(articleState.value.articles)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onAboutButtonClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Article") },
        actions = {
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "About Device Button"
                )
            }
        }
    )
}

@Composable
fun ArticleListView(articles: List<Article>) {

    LazyColumn(Modifier.fillMaxSize()) {
        items(articles) {
            ArticleItemView(articles = it)
        }
    }
}

@Composable
fun ArticleItemView(articles: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = articles.imageUrl,
            contentDescription = articles.title
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = articles.title)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = articles.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = articles.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun Loader() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, color = Color.Red, textAlign = TextAlign.Center)
        )
    }
}

