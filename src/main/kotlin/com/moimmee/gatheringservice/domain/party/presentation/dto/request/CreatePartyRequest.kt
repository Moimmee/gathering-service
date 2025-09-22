package com.moimmee.gatheringservice.domain.party.presentation.dto.request

import java.time.LocalDateTime

data class CreatePartyRequest(
    val name: String,
    val description: String,
    val startedAt: LocalDateTime,
    val endedAt: LocalDateTime,
    val latitude: Double,
    val longitude: Double,
    val limit: Int? = null,
)
