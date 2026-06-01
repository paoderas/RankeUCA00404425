package com.pdm0126.rankeuca00404425.model

data class Option(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int
)

fun OptionDto.toDomain(): Option {
    return Option(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        votes = this.votes
    )
}