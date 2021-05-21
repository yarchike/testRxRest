package com.martynov.testrxrest.repository

import io.reactivex.Single
import retrofit2.http.POST

interface Repository {
    fun sendTest(test: String): Single<String>
}