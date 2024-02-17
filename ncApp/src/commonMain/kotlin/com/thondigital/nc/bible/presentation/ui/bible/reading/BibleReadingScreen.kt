package com.thondigital.nc.bible.presentation.ui.bible.reading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import com.ipsoft.bibliasagrada.domain.model.Verse
import com.thondigital.nc.bible.presentation.ui.bible.BibleBooksScreenModel

class BibleReadingScreen(
    private val bookName: String,
    private val bookAbbrev: String,
    private val chapterQuantity: Int,
) : Screen {
    @Composable
    override fun Content() {

        val screenModel = getScreenModel<BibleBooksScreenModel>()
        val chapterState: State<ChapterResponse?> =
            screenModel.chapter
        val currentChapter: State<Int> = screenModel.currentChapter
        val fontSizeState: State<TextUnit> = screenModel.fontSize
        val showBottomBar = remember {
            mutableStateOf(true)
        }

        with(screenModel) {
            getBookChapter(bookName, bookAbbrev, currentChapter.value)
            setCurrentChapter(currentChapter.value)
        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            toggleNavigationMenusVisibility(showBottomBar)
                        }
                    )
                }
        ) {
            LazyColumn {
                items(chapterState.value?.verses ?: emptyList()) { verse ->
                    VerseItem(
                        verse,
                        fontSizeState,
                        { toggleNavigationMenusVisibility(showBottomBar) }
                    )
                }
                if (showBottomBar.value) item { Spacer(modifier = Modifier.height(64.dp)) } else {
                    item { Spacer(modifier = Modifier.height(8.dp)) }
                }
            }
            BottomMenu(
                screenModel,
                chapterQuantity,
                currentChapter,
                showBottomBar
            )
        }
    }

}


fun toggleNavigationMenusVisibility(
    showBottomBar: MutableState<Boolean>,
) {
    showBottomBar.value = !showBottomBar.value
}

@Composable
fun BottomMenu(
    viewModel: BibleBooksScreenModel,
    chapterQuantity: Int,
    currentChapter: State<Int>,
    showBottomBar: MutableState<Boolean>,
) {

    var expanded by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = showBottomBar.value,
        enter = slideInVertically(initialOffsetY = { 40 }),
        exit = slideOutVertically(targetOffsetY = { 40 })
    ) {

        Card(
            elevation = 8.dp,
            modifier = Modifier
                .wrapContentSize(align = Alignment.BottomCenter)
                .fillMaxWidth()

        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(16.dp)
            ) {
                currentChapter.value.let { currentChapter ->
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            if (currentChapter > 1) {
                                viewModel.previousChapter()
                            }
                        }
                    )
                }
                Row(
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    }
                ) {
                    //                    Icon(
                    //                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_font_size),
                    //                        contentDescription = null
                    //                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "Tamanho da fonte"
                    )
                }
                currentChapter.value.let { currentChapter ->
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            if (chapterQuantity > currentChapter) {
                                viewModel.nextChapter()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun VerseItem(
    verse: Verse,
    fontSize: State<TextUnit>,
    onTap: () -> Unit,
    onLongClick: ((verse: Verse) -> Unit)? = null,
    ) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    if (onLongClick != null) {
                        onLongClick(verse)
                    }
                }, onTap = { onTap() })
            }
    ) {
        Text(text = "${verse.number}. ${verse.text}", fontSize = fontSize.value)
    }
}

