package com.pdm0126.rankeuca00404425.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

object NetworkModule {
    val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }
    val apiService = ApiService(client)
    val optionRepository = OptionRepositoryImplementation(apiService)


}