package com.moimmee.gatheringservice.domain.party.domain.repository

import com.moimmee.gatheringservice.domain.party.domain.entity.PartyEntity
import com.moimmee.gatheringservice.domain.party.domain.enums.PartyCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface PartyJpaRepository : JpaRepository<PartyEntity, UUID> {
    @Query(
        """
        SELECT p FROM PartyEntity p
        WHERE ST_Distance_Sphere(Point(:longitude, :latitude), Point(p.longitude, p.latitude)) <= 10000
        AND (:category IS NULL OR p.category = :category)
        AND p.endedAt > :now
        ORDER BY p.startedAt ASC
        """
    )
    fun findPartiesNear(
        @Param("latitude") latitude: Double,
        @Param("longitude") longitude: Double,
        @Param("category") category: PartyCategory?,
        @Param("now") now: LocalDateTime
    ): List<PartyEntity>
}