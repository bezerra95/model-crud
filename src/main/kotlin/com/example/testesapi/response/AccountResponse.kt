package com.example.testesapi.response

data class AccountResponse(
    var id: Long? = null,
    val name: String,
    val document: String,
    val phone: String
)