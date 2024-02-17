package com.thondigital.nc.bible.presentation.ui.bible

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.bible.domain.common.constants.MAX_FONT_SIZE
import com.thondigital.nc.bible.domain.common.constants.MIN_FONT_SIZE
import com.thondigital.nc.bible.domain.core.extension.removeAccents
import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import com.thondigital.nc.bible.domain.usecases.GetBooksUseCase
import com.ipsoft.bibliasagrada.domain.usecases.GetChapterUseCase
import com.ipsoft.bibliasagrada.domain.usecases.GetFontSizeUseCase
import com.ipsoft.bibliasagrada.domain.usecases.StoreFontSizeUseCase

class BibleBooksScreenModel(
    private val getBooksUseCase: GetBooksUseCase,
    private val getChapterUseCase: GetChapterUseCase,
    private val getFontSizeUseCase: GetFontSizeUseCase,
    private val storeFontSizeUseCase: StoreFontSizeUseCase,
) : ScreenModel {

    private var bruteFontSize = 16

    var fontSize = mutableStateOf(bruteFontSize.sp)
        private set
    var currentChapter = mutableStateOf(1)
        private set
    var books = mutableStateOf<List<BookResponse>>(emptyList())
        private set
    var lastSearch = mutableStateOf("")
        private set
    var filteredBooks = mutableStateOf<List<BookResponse>>(emptyList())
        private set
    var chapter = mutableStateOf<ChapterResponse?>(null)
        private set


    init {
        getFontSize()
        getBooks()
    }


    fun setCurrentChapter(chapter: Int) {
        currentChapter.value = chapter
    }

    fun nextChapter() {
        currentChapter.value.let {
            currentChapter.value = it + 1
        }
    }

    fun previousChapter() {
        currentChapter.value.let {
            if (it > 1) currentChapter.value = it - 1
        }
    }

    fun getBooks() {
        return getBooksUseCase(
            UseCase.None(), screenModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleBooksFetchSuccess
            )
        }
    }

    fun getBookChapter(bookName: String, bookAbbrev: String, chapterId: Int) {
        chapter.value = null
        return getChapterUseCase(
            GetChapterUseCase.Params(bookName, bookAbbrev, chapterId), screenModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleFetchBookChapterSuccess
            )
        }
    }

    fun searchBook(search: String) {
        filteredBooks.value = books.value.filter {
            it.name.removeAccents().contains(search, true) || it.abbrev.pt.contains(
                search, true
            )
        }

    }


    private fun getFontSize() {
        getFontSizeUseCase(
            UseCase.None(), screenModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleFontSizeFetchSuccess
            )
        }
    }

    private fun storeFontSize() {
        storeFontSizeUseCase(
            StoreFontSizeUseCase.Params(bruteFontSize), screenModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleFontSizeStoreSuccess
            )
        }
    }

    private fun handleFontSizeFetchSuccess(fontSize: Int) {
        bruteFontSize = fontSize
        this.fontSize.value = bruteFontSize.sp
    }

    private fun handleFetchBookChapterSuccess(chapterResponse: ChapterResponse) {
        chapter.value = chapterResponse

    }

    private fun handleBooksFetchSuccess(bookResponse: List<BookResponse>) {
        books.value = bookResponse
    }

    fun clearFilteredBooks() {
        filteredBooks.value = emptyList()
    }

    fun updateLastSearch(text: String) {
        lastSearch.value = text
    }

    fun increaseFontSize() {
        if (bruteFontSize < MAX_FONT_SIZE) {
            fontSize.value = (++bruteFontSize).sp
            storeFontSize()
        }
    }

    fun decreaseFontSize() {
        if (bruteFontSize > MIN_FONT_SIZE) {
            fontSize.value = (--bruteFontSize).sp
            storeFontSize()
        }
    }
}
