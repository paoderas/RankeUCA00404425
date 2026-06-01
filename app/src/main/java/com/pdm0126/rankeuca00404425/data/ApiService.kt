package com.pdm0126.rankeuca00404425.data

import com.pdm0126.rankeuca00404425.model.OptionDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.utils.EmptyContent.headers


class ApiService(
    private val client: HttpClient = HttpClient {
        defaultRequest {
            headers.append("Authorization", "Bearer 343afa4a-71cb-4df6-91e3-26db209cf925")
        }
    }
) {
    suspend fun fetchOptions(): List<OptionDto> {
        return client
            .get("https://qjcxdvfzyseuvezacxsd.supabase.co/functions/v1/rankeuca/options")
            .body()
    }
}
