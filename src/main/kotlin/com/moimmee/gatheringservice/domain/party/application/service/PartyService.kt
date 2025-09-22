package com.moimmee.gatheringservice.domain.party.application.service

import com.moimmee.gatheringservice.domain.party.domain.entity.PartyEntity
import com.moimmee.gatheringservice.domain.party.domain.repository.PartyJpaRepository
import com.moimmee.gatheringservice.domain.party.presentation.dto.request.CreatePartyRequest
import com.moimmee.gatheringservice.infra.adapter.user.service.UserService
import com.moimmee.gatheringservice.infra.security.holder.ContextHolder
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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

        val newParty = PartyEntity(
            ownerId = user.id,
            name = request.name,
            description = request.description,
            startedAt = request.startedAt,
            endedAt = request.endedAt,
            latitude = request.latitude,
            longitude = request.longitude
        )

        val savedParty = partyJpaRepository.save(newParty)

        return savedParty.id
    }
}