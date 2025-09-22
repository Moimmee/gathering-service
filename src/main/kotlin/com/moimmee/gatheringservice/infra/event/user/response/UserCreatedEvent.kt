package com.moimmee.gatheringservice.infra.event.user.response

data class UserCreatedEvent(
    val id: Long,
    val name: String,
    val email: String,
)