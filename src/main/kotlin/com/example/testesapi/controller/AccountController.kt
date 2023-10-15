package com.example.testesapi.controller

import com.example.testesapi.model.Account
import com.example.testesapi.request.AccountRequest
import com.example.testesapi.response.AccountResponse
import com.example.testesapi.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController {

    @Autowired
    private lateinit var accountService: AccountService

    @PostMapping
    fun createAccount(@RequestBody accountRequest: AccountRequest): ResponseEntity<AccountResponse> {
        val createdAccount = accountService.createAccount(accountRequest)

        val accountResponse = AccountResponse(
            createdAccount.id,
            createdAccount.name,
            createdAccount.document,
            createdAccount.phone
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse)
    }

    @GetMapping
    fun getAll(): List<Account> = accountService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Account> =
        accountService.getById(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun updateAccount(@PathVariable id: Long, @RequestBody accountRequest: AccountRequest): ResponseEntity<Account> {
        val updatedAccount = accountService.updateAccount(id, accountRequest)
        return if (updatedAccount != null) {
            ResponseEntity.ok(updatedAccount)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        accountService.delete(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}

