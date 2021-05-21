package com.martynov.testrxrest.api

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("/test")
    fun sendTest(@Body test: String): Single<String>
}