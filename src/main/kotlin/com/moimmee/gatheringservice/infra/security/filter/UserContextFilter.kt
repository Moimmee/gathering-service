package com.moimmee.gatheringservice.infra.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class UserContextFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if ("true" != request.getHeader("X-Internal-Call")) {
            // 클라이언트 직접 접근 차단
            response.status = 403
            return
        }

        val userId = request.getHeader("X-User-Id")
        val role = request.getHeader("X-User-Role")

        val authorities = listOf(SimpleGrantedAuthority("ROLE_$role"))
        val authentication = UsernamePasswordAuthenticationToken(userId.toLong(), null, authorities)

        SecurityContextHolder.getContext().authentication = authentication
    }
}