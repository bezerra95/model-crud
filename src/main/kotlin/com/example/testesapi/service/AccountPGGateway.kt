package com.example.testesapi.service

import com.example.testesapi.model.Account
import com.example.testesapi.repository.AccountRepository
import org.springframework.stereotype.Component

@Component
class AccountPGGateway(private val repository: AccountRepository) : AccountGateway {

    override fun getAll(): List<Account> {
        return repository.findAll()
    }

    override fun getById(id: Long): Account {
        return repository.getById(id)
    }

    override fun delete(id: Long) {
        repository.findById(id).map {
            repository.delete(it)
        }.orElseThrow{ throw RuntimeException("Id not found $id") }
    }

    override fun save(account: Account): Account {
       return repository.save(account)
    }
}
