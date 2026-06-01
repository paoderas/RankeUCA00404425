package com.pdm0126.rankeuca00404425.data

import com.pdm0126.rankeuca00404425.model.Option

interface OptionRepository {
    suspend fun getOptions(): Result<List<Option>>
}
