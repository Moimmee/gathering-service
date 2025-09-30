package com.moimmee.gatheringservice.domain.party.application.service

import com.moimmee.gatheringservice.domain.party.domain.entity.PartyEntity
import com.moimmee.gatheringservice.domain.party.domain.entity.PartyParticipantEntity
import com.moimmee.gatheringservice.domain.party.domain.error.PartyErrorCodeCode
import com.moimmee.gatheringservice.domain.party.domain.repository.PartyJpaRepository
import com.moimmee.gatheringservice.domain.party.domain.repository.PartyParticipantJpaRepository
import com.moimmee.gatheringservice.infra.adapter.user.domain.error.UserErrorCode
import com.moimmee.gatheringservice.infra.adapter.user.service.UserService
import com.moimmee.gatheringservice.infra.exception.CustomException
import com.moimmee.gatheringservice.infra.security.holder.ContextHolder
import kotlinx.coroutines.runBlocking
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
class PartyParticipantService(
    private val partyParticipantJpaRepository: PartyParticipantJpaRepository,
    private val contextHolder: ContextHolder,
    private val userService: UserService,
    private val partyJpaRepository: PartyJpaRepository
) {
    @Transactional
    fun joinParty(partyId: UUID) {
        val userId = contextHolder.getCurrentUserId()
        val party = findParty(partyId)

        val user = runBlocking { userService.getUserById(userId) }
            ?: throw CustomException(UserErrorCode.USER_NOT_FOUND)

        partyParticipantJpaRepository.save(
            PartyParticipantEntity(
                partyId = party.id!!,
                userId = user.id,
                joinedAt = LocalDateTime.now(),
            )
        )
    }

    @Transactional
    fun leaveParty(partyId: UUID) {
        val userId = contextHolder.getCurrentUserId()
        val party = findParty(partyId)

        val user = runBlocking { userService.getUserById(userId) }
            ?: throw CustomException(UserErrorCode.USER_NOT_FOUND)

        partyParticipantJpaRepository.deleteByUserIdAndPartyId(user.id, party.id!!)
    }

    private fun findParty(partyId: UUID): PartyEntity {
        return partyJpaRepository.findByIdOrNull(partyId)
            ?: throw CustomException(PartyErrorCodeCode.PARTY_NOT_FOUND)
    }
}