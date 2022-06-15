package com.cibo.cibo.repository

import com.cibo.cibo.data.network.ApiService
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getCategories(filialId: String, language: String) =
        apiService.getCategories(filialId, language)

}
