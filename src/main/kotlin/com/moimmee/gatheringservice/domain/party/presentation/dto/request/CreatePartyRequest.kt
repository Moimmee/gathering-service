package com.moimmee.gatheringservice.domain.party.presentation.dto.request

import com.moimmee.gatheringservice.domain.party.domain.enums.PartyCategory
import java.time.LocalDateTime

data class CreatePartyRequest(
    val name: String,
    val description: String,
    val startedAt: LocalDateTime,
    val endedAt: LocalDateTime,
    val latitude: Double,
    val longitude: Double,
    val limitMember: Int? = null,
    val category: PartyCategory
)
