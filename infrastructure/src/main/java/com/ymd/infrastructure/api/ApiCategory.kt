package com.ymd.infrastructure.api

import retrofit2.http.GET

interface ApiCategory {

    @GET("categories")
    suspend fun getAllCategories(): List<String>

}