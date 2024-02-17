package com.thondigital.nc.bible.presentation.ui.bible.chapters

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.thondigital.nc.bible.presentation.ui.bible.BibleBooksScreenModel
import com.thondigital.nc.bible.presentation.ui.bible.reading.BibleReadingScreen

class ListChaptersScreen(
    private val bookName: String,
    private val bookAbbrev: String,
    private val chapterQuantity: Int,
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<BibleBooksScreenModel>()

        val fontSizeState: State<TextUnit> = viewModel.fontSize


        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 70.dp)) {
                items(chapterQuantity) { index ->
                    val currentChapter = index + 1
                    ChapterItem(currentChapter, fontSizeState) {
                        navigator.push(
                            BibleReadingScreen(
                                bookName = bookName,
                                bookAbbrev = bookAbbrev,
                                chapterQuantity = chapterQuantity
                            )
                        )
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChapterItem(chapter: Int, fontSizeState: State<TextUnit>, onBookClick: () -> Unit) {

    Card(
        elevation = 5.dp,
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp),
        onClick = onBookClick
    ) {
        Text(
            text = chapter.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp),
            fontSize = fontSizeState.value
        )
    }
}
