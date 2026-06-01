package com.pdm0126.rankeuca00404425.data

import com.pdm0126.rankeuca00404425.model.Option
import com.pdm0126.rankeuca00404425.model.toDomain

class OptionRepositoryImplementation(private val apiService: ApiService): OptionRepository {
    override suspend fun getOptions(): Result<List<Option>> {
        return try {
            val response = apiService.fetchOptions()
            val domainOptions = response.map { it.toDomain()}

            Result.success(domainOptions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}