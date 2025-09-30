package com.moimmee.gatheringservice.infra.exception.response

import com.moimmee.gatheringservice.infra.exception.CustomException
import org.springframework.http.ResponseEntity

data class ErrorResponse(
    val code: String,
    val status: Int,
    val message: String
) {
    companion object {
        fun of(exception: CustomException) = ResponseEntity.status(exception.status).body(
            ErrorResponse(
                code = exception.code,
                status = exception.status,
                message = exception.message
            )
        )
    }
}