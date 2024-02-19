package com.example.testesapi.service

import com.example.testesapi.model.Account
import org.springframework.data.domain.Sort

interface AccountGateway {
    fun getAll(): List<Account>
    fun getById(id: Long): Account
    fun delete(id: Long)
    fun save (account: Account): Account
    fun getAllSort(sort: Sort): List<Account>
}
