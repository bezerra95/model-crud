package com.example.testesapi.service

import com.example.testesapi.model.Account
import com.example.testesapi.request.AccountRequest
import java.util.*

interface AccountGateway {
    fun getAll(): List<Account>
    fun getById(id: Long): Account
    fun delete(id: Long)
    fun save (account: Account): Account
}
