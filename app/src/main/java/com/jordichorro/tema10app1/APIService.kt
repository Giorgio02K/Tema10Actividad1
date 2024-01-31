package com.jordichorro.tema10app1

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getCatFact(@Url url: String): Response<CatResponse>

    @GET("cat")
    suspend fun getRandomCatImage(): Response<CatImageResponse>
}
