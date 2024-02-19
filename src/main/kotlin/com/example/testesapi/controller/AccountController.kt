package com.example.testesapi.controller

import com.example.testesapi.request.AccountRequest
import com.example.testesapi.request.toResponse
import com.example.testesapi.response.AccountResponse
import com.example.testesapi.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.*
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

        return ResponseEntity.ok(account.toResponse())
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<AccountResponse>> {
        val response = accountService.getAll()

        return ResponseEntity.ok(response.map { it.toResponse() })
    }

    @GetMapping("/page")
    fun getAllPage(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<AccountResponse>> {
        val response = accountService.getAll()

        val pageable: Pageable = PageRequest.of(page, size)
        val startIndex = page * size
        val endIndex = (startIndex + size).coerceAtMost(response.size)
        val pagedResponses = response.subList(startIndex, endIndex)

        val accounts = PageImpl(pagedResponses, pageable, response.size.toLong())

        return ResponseEntity.ok(accounts.map { account -> account.toResponse() })
    }

    @GetMapping("/sort")
    fun getAllSort(
        @RequestParam(required = false) sortField: String?,
        @RequestParam(defaultValue = "asc") sortDirection: String?
    ): ResponseEntity<List<AccountResponse>> {

        val sort: Sort = if (sortField != null) {
            if (sortDirection == "desc") {
                Sort.by(Sort.Order.desc(sortField))
            } else {
                Sort.by(Sort.Order.asc(sortField))
            }
        } else {
            Sort.by(Sort.Order.asc("startTime"))
        }
        val response = accountService.getAllSort(sort)

        return ResponseEntity.ok(response.map { it.toResponse() })
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<AccountResponse> {
        val account = accountService.getById(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(account.toResponse())
    }

    @PutMapping("/{id}")
    fun updateAccount(
        @PathVariable id: Long,
        @RequestBody accountRequest: AccountRequest
    ): ResponseEntity<AccountResponse> {
        accountService.getById(id) ?: return ResponseEntity.notFound().build()

        val account = accountService.updateAccount(id, accountRequest)

        return ResponseEntity.ok(account.toResponse())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<AccountResponse> {
        val account = accountService.getById(id) ?: return ResponseEntity.notFound().build()

        accountService.delete(account.id!!)

        return ResponseEntity.ok(account.toResponse())
    }
}
