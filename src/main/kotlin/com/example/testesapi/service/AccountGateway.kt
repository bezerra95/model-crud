package com.example.testesapi.service

import com.example.testesapi.model.Account
import java.util.*

interface AccountGateway {

    fun create(account: Account): Account

    fun getAll(): List<Account>

    fun getById(id: Long): Optional<Account>

    fun update(id: Long, account: Account): Optional<Account>

    fun delete(id: Long)

}