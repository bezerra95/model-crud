package com.example.testesapi.controller

import com.example.testesapi.model.Account
import com.example.testesapi.request.AccountRequest
import com.example.testesapi.request.toResponde
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
        val account = accountService.createAccount(accountRequest)

        return ResponseEntity.ok(account.toResponde())
    }

    @GetMapping
    fun getAll(): List<Account> = accountService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<AccountResponse> {
        val account = accountService.getById(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(account.toResponde())
    }

    @PutMapping("/{id}")
    fun updateAccount(@PathVariable id: Long, @RequestBody accountRequest: AccountRequest): ResponseEntity<AccountResponse> {
        accountService.getById(id) ?: return ResponseEntity.notFound().build()

        val account = accountService.updateAccount(id, accountRequest)

        return ResponseEntity.ok(account.toResponde())
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<AccountResponse> {
        val account = accountService.getById(id) ?: return ResponseEntity.notFound().build()

        accountService.delete(account.id!!)

        return ResponseEntity.ok(account.toResponde())
    }
}
