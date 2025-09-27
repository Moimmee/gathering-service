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
interface PartyJpaRepository : JpaRepository<PartyEntity, UUID>