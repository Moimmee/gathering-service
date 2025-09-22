package com.moimmee.gatheringservice.infra.adapter.user.service

import com.moimmee.gatheringservice.infra.grpc.user.UserServiceClient

interface UserService {
    suspend fun getUserById(userId: Long): UserServiceClient.UserResponse?
}