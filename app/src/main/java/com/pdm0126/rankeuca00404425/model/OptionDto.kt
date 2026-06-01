package com.pdm0126.rankeuca00404425.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OptionDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("votes") val votes: Int
)