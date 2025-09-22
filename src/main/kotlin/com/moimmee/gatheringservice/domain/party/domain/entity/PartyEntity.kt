package com.moimmee.gatheringservice.domain.party.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "parties")
data class PartyEntity(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "owner_id", nullable = false)
    val ownerId: Long,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "description", nullable = false)
    val description: String,

    @Column(name = "started_at", nullable = false)
    val startedAt: LocalDateTime,

    @Column(name = "ended_at", nullable = false)
    val endedAt: LocalDateTime,

    @Column(name = "latitude", nullable = false)
    val latitude: Double,

    @Column(name = "longitude", nullable = false)
    val longitude: Double,
)