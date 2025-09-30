package com.moimmee.gatheringservice.domain.party.domain.entity

import com.moimmee.gatheringservice.infra.common.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "party_participants")
data class PartyParticipantEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "party_id", nullable = false)
    val partyId: UUID,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val joinedAt: LocalDateTime,
) : BaseEntity()