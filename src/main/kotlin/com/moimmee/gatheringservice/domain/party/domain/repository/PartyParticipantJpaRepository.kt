package com.moimmee.gatheringservice.domain.party.domain.repository

import com.moimmee.gatheringservice.domain.party.domain.entity.PartyParticipantEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PartyParticipantJpaRepository : JpaRepository<PartyParticipantEntity, Long> {
    fun deleteByUserIdAndPartyId(partyId: Long, userId: UUID)
}