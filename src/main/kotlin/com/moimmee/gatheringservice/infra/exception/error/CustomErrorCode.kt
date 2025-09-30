package com.moimmee.gatheringservice.infra.exception.error

import org.springframework.http.HttpStatus

interface CustomErrorCode {
    val status: HttpStatus
    val message: String
}