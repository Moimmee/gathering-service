package com.moimmee.gatheringservice.domain.party.domain.repository

import com.moimmee.gatheringservice.domain.party.domain.entity.PartyEntity
import com.moimmee.gatheringservice.domain.party.domain.entity.QPartyEntity
import com.moimmee.gatheringservice.domain.party.domain.enums.PartyCategory
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PartyQueryRepository(
    private val queryFactory: JPAQueryFactory
) {
    private val partyEntity = QPartyEntity.partyEntity

    fun findNearbyParties(
        longitude: Double,
        latitude: Double,
        category: PartyCategory?,
        now: LocalDateTime
    ): List<PartyEntity> {
        return queryFactory
            .selectFrom(partyEntity)
            .where(
                Expressions.booleanTemplate(
                    "ST_Distance_Sphere(Point({0}, {1}), Point({2}, {3})) <= 10000",
                    longitude, latitude, partyEntity.longitude, partyEntity.latitude
                ),
                if (category != null) partyEntity.category.eq(category) else null,
                partyEntity.endedAt.gt(now)
            )
            .orderBy(partyEntity.startedAt.asc())
            .fetch()
    }
}