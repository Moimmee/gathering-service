package com.moimmee.gatheringservice.domain.party.domain.error

import com.moimmee.gatheringservice.infra.exception.error.CustomErrorCode
import org.springframework.http.HttpStatus

enum class PartyErrorCodeCode(override val status: HttpStatus, override val message: String) : CustomErrorCode {
    PARTY_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 파티입니다."),
}