package com.moimmee.gatheringservice.infra.adapter.user.domain

data class User(
    val id: Long,
    val name: String,
    val role: String,
    val gender: String,
    val email: String,
)
