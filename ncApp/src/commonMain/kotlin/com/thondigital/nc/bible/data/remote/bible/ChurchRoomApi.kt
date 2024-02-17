package com.thondigital.nc.bible.data.remote.bible

import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse

interface ChurchRoomApi {

    @GET("books")
    fun getBooks(): Call<List<BookResponse>>

    @GET
    fun getChapter(@Url url: String): Call<ChapterResponse>
}
