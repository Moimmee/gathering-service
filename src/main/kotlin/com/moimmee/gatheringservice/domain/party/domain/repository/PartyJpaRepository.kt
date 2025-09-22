package com.moimmee.gatheringservice.domain.party.domain.repository

import com.moimmee.gatheringservice.domain.party.domain.entity.PartyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PartyJpaRepository : JpaRepository<PartyEntity, UUID>