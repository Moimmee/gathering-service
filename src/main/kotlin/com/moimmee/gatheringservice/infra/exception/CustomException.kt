package com.moimmee.gatheringservice.infra.exception

import com.moimmee.gatheringservice.infra.exception.error.CustomErrorCode

class CustomException(val error: CustomErrorCode, vararg args: Any) : RuntimeException() {
    val code = (error as Enum<*>).name
    val status = error.status.value()
    override val message = error.message.format(*args)
}