package com.example.chemp_podject.api

data class BlockModel(
    val bio: String,
    val category: String,
    val description: String,
    val id: Int,
    val name: String,
    val preparation: String,
    val price: String,
    val time_result: String
) :java.io.Serializable
