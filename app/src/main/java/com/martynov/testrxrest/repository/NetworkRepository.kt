package com.martynov.testrxrest.repository

import com.martynov.testrxrest.api.API
import io.reactivex.Single

class NetworkRepository(private val api: API) : Repository{
    override fun sendTest(test: String): Single<String> {
        return api.sendTest(test)
    }
}