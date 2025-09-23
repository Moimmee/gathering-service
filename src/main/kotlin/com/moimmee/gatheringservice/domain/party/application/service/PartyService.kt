package com.moimmee.gatheringservice.domain.party.application.service

import com.moimmee.gatheringservice.domain.party.domain.entity.PartyEntity
import com.moimmee.gatheringservice.domain.party.domain.enums.PartyCategory
import com.moimmee.gatheringservice.domain.party.domain.repository.PartyJpaRepository
import com.moimmee.gatheringservice.domain.party.presentation.dto.request.CreatePartyRequest
import com.moimmee.gatheringservice.domain.party.presentation.dto.response.PartyResponse
import com.moimmee.gatheringservice.infra.adapter.user.service.UserService
import com.moimmee.gatheringservice.infra.security.holder.ContextHolder
import kotlinx.coroutines.runBlocking
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class PartyService(
    private val partyJpaRepository: PartyJpaRepository,
    private val userService: UserService,
    private val contextHolder: ContextHolder
) {
    @Transactional
    fun createParty(request: CreatePartyRequest): UUID? {
        val userId = contextHolder.getCurrentUserId()

        val user = runBlocking { userService.getUserById(userId) } ?: throw IllegalArgumentException("Can't find user")

        val saved = partyJpaRepository.save(
            PartyEntity(
                ownerId = user.id,
                name = request.name,
                description = request.description,
                startedAt = request.startedAt,
                endedAt = request.endedAt,
                latitude = request.latitude,
                longitude = request.longitude,
                category = request.category,
                limit = request.limit
            )
        )

        return saved.id
    }

    @Transactional(readOnly = true)
    fun getParties(latitude: Double, longitude: Double, category: PartyCategory?): List<PartyResponse> {
        return partyJpaRepository.findPartiesNear(
            latitude = latitude,
            longitude = longitude,
            category = category,
            now = LocalDateTime.now()
        ).map { it.toResponse() }
    }

    fun getParty(partyId: UUID): PartyResponse {
        return partyJpaRepository.findByIdOrNull(partyId)?.toResponse()
            ?: throw IllegalArgumentException("Party not found")
    }

    private fun PartyEntity.toResponse() = PartyResponse(
        id = this.id!!,
        name = this.name,
        description = this.description,
        startedAt = this.startedAt,
        endedAt = this.endedAt,
        latitude = this.latitude,
        longitude = this.longitude,
        category = this.category,
        limit = this.limit,
    )
}