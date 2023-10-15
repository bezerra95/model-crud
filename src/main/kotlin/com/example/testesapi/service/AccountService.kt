package com.example.testesapi.service

import com.example.testesapi.model.Account
import com.example.testesapi.repository.AccountRepository
import com.example.testesapi.request.AccountRequest
import com.example.testesapi.response.AccountResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class AccountService {

    @Autowired
    private lateinit var accountRepository: AccountRepository

    fun createAccount(accountRequest: AccountRequest): AccountResponse {
        val account = Account(
            name = accountRequest.name,
            document = accountRequest.document,
            phone = accountRequest.phone
        )

        val createdAccount = accountRepository.save(account)

        return AccountResponse(
            createdAccount.id,
            createdAccount.name,
            createdAccount.document,
            createdAccount.phone
        )
    }

    fun getAll(): List<Account> {
        return accountRepository.findAll()
    }

    fun getById(id: Long): Optional<Account> {
        return accountRepository.findById(id)
    }

    fun updateAccount(id: Long, accountRequest: AccountRequest): Account {
        return accountRepository.save(Account(
            id = id,
            name = accountRequest.name,
            phone = accountRequest.phone,
            document = accountRequest.document
        ))
    }

    fun delete(id: Long) {
        accountRepository.findById(id).map {
            accountRepository.delete(it)
        }.orElseThrow{ throw RuntimeException("Id not found $id") }
    }

}