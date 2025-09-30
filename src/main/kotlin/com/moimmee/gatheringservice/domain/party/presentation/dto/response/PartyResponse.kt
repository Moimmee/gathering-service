package com.moimmee.gatheringservice.domain.party.presentation.dto.response

import com.moimmee.gatheringservice.domain.party.domain.enums.PartyCategory
import java.time.LocalDateTime
import java.util.*

data class PartyResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val startedAt: LocalDateTime,
    val endedAt: LocalDateTime,
    val latitude: Double,
    val longitude: Double,
    val limit: Int? = null,
    val category: PartyCategory
)
