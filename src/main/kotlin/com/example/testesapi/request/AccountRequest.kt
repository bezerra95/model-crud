package com.example.testesapi.request

import com.example.testesapi.model.Account
import com.example.testesapi.response.AccountResponse

data class AccountRequest(
    val name: String,
    val document: String,
    val phone: String
)

fun Account.toResponde(): AccountResponse {
    return AccountResponse(
        id = this.id!!,
        name = this.name,
        document = this.document,
        phone = this.phone
    )
}
