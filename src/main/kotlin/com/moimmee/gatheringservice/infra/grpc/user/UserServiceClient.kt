package com.moimmee.gatheringservice.infra.grpc.user

import com.moimmee.gatheringservice.infra.adapter.user.service.UserService
import com.moimmee.proto.user.UserServiceGrpcKt
import com.moimmee.proto.user.UserServiceProto.GetUserRequest
import com.moimmee.proto.user.UserServiceProto.User
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Component

@Component
class UserServiceClient : UserService {
    @GrpcClient("user-service")
    private lateinit var userServiceStub: UserServiceGrpcKt.UserServiceCoroutineStub

    override suspend fun getUserById(userId: Long): UserResponse? {
        val request = GetUserRequest.newBuilder()
            .setUserId(userId.toString())
            .build()

        val response = userServiceStub.getUser(request)
        return response.user.toResponse()
    }

    private suspend fun User.toResponse(): UserResponse {
        return UserResponse(
            id = this.id.toLong(),
            username = this.name
        )
    }

    data class UserResponse(
        val id: Long,
        val username: String,
    )
}