package com.moimmee.gatheringservice.infra.adapter.user.domain.error

import com.moimmee.gatheringservice.infra.exception.error.CustomErrorCode
import org.springframework.http.HttpStatus

enum class UserErrorCode(override val status: HttpStatus, override val message: String) : CustomErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 유저입니다.")
}