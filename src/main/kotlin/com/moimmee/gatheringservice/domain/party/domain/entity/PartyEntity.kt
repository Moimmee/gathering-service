package com.moimmee.gatheringservice.domain.party.domain.entity

import com.moimmee.gatheringservice.domain.party.domain.enums.PartyCategory
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
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

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    val category: PartyCategory,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = null,

    @Column(name = "limit", nullable = true)
    val limit: Int? = null,
)