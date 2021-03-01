package com.daffa.weekone.data

import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("todos")
    fun getTodos(): Call<List<Todo>>
}