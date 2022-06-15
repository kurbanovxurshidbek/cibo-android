package com.cibo.cibo.data.network

import com.cibo.cibo.model.CategoryList
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("category/public/list/{filialId}/language/{language}")
    suspend fun getCategories(
        @Path("filialId") filialId: String,
        @Path("language") language: String
    ): CategoryList

}