package com.moimmee.gatheringservice.infra.security.holder

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class ContextHolder {
    fun getCurrentUserId(): Long {
        return SecurityContextHolder.getContext().authentication.principal as Long
    }
}