package com.example.testesapi.service

import com.example.testesapi.enum.EntityStatus
import com.example.testesapi.model.Account
import com.example.testesapi.request.AccountRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class AccountService {

    @Autowired
    private lateinit var accountGateway: AccountGateway

    fun createAccount(accountRequest: AccountRequest): Account {
        val account = Account(
            name = accountRequest.name,
            document = accountRequest.document,
            phone = accountRequest.phone,
            status = EntityStatus.ACTIVE
        )
        return accountGateway.save(account)
    }

    fun getAll(): List<Account> {
        return accountGateway.getAll().filter { it.status == EntityStatus.ACTIVE }
    }

    fun getById(id: Long): Account? {
        return accountGateway.getById(id)
    }

    fun updateAccount(id: Long, accountRequest: AccountRequest): Account {
        return accountGateway.save(
            Account(
            id = id,
            name = accountRequest.name,
            phone = accountRequest.phone,
            document = accountRequest.document,
            status = EntityStatus.ACTIVE
            )
        )
    }

    fun delete(id: Long) {
        return accountGateway.delete(id)
    }

    fun getAllSort(sort: Sort): List<Account> {
        return accountGateway.getAllSort(sort)
    }
}
