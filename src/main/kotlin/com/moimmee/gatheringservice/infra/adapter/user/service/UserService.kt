package com.moimmee.gatheringservice.infra.adapter.user.service

import com.moimmee.gatheringservice.infra.adapter.user.domain.User

interface UserService {
    suspend fun getUserById(userId: Long): User?
}
