package com.example.testesapi.response

import com.example.testesapi.request.AccountRequest

data class AccountResponse(
    var id: Long,
    val name: String,
    val document: String,
    val phone: String
)
