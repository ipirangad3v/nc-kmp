package com.thondigital.nc.bible.presentation.ui.bible.books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.thondigital.nc.bible.presentation.ui.bible.BibleBooksScreenModel
import com.thondigital.nc.bible.presentation.ui.bible.chapters.ListChaptersScreen

@OptIn(ExperimentalComposeUiApi::class)
class BibleBooksScreen(private val keyboardController: SoftwareKeyboardController?) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<BibleBooksScreenModel>()

        val fontSizeState: State<TextUnit> = screenModel.fontSize

        val booksState: State<List<BookResponse>> =
            screenModel.books
        val filteredBooksState: State<List<BookResponse>?> = screenModel.filteredBooks

        val textState =
            remember { mutableStateOf(screenModel.lastSearch.value?.let { TextFieldValue(it) }) }

        SearchView(textState, screenModel, keyboardController) {
            textState.value = TextFieldValue("")
            screenModel.searchBook("")
        }
        LazyColumn {
            items(filteredBooksState.value ?: booksState.value) { book ->
                BookItem(book, fontSizeState) {
                    keyboardController?.hide()
                    navigator.push(
                        ListChaptersScreen(
                            book.name,
                            book.abbrev.pt,
                            book.chapters

                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookItem(book: BookResponse, fontSizeState: State<TextUnit>, onBookClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(), onClick = onBookClick
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = book.name, fontSize = fontSizeState.value)
            Text(text = book.abbrev.pt, fontSize = fontSizeState.value)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(
    state: MutableState<TextFieldValue?>,
    viewModel: BibleBooksScreenModel,
    keyboardController: SoftwareKeyboardController?,
    onDeleteClick: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        state.value?.let {
            TextField(
                value = it,
                onValueChange = { value: TextFieldValue ->
                    state.value = value
                    viewModel.updateLastSearch(value.text)
                    if (value.text.isBlank()) viewModel.clearFilteredBooks() else viewModel.searchBook(
                        value.text
                    )
                },
                label = {
                    Text("Procurar livro")
                },
                trailingIcon = {
                    if (state.value?.text?.isBlank() == false) Icon(
                        Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onDeleteClick()
                        }
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),

                )
        }
    }
}
