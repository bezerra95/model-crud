package com.example.testesapi.model

import com.example.testesapi.enum.EntityStatus
import javax.persistence.*

@Entity(name = "accounts")
data class Account (
    @Id @GeneratedValue
    var id: Long? = null,

    val name: String,

    val document: String,

    val phone: String,

    @Enumerated(EnumType.STRING)
    var status: EntityStatus
)
