package com.moimmee.gatheringservice.infra.grpc.user

import com.moimmee.gatheringservice.infra.adapter.user.domain.User
import com.moimmee.gatheringservice.infra.adapter.user.service.UserService
import com.moimmee.proto.user.UserServiceGrpcKt
import com.moimmee.proto.user.UserServiceProto.GetUserRequest
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Component

@Component
class UserServiceClient : UserService {
    @GrpcClient("user-service")
    private lateinit var userServiceStub: UserServiceGrpcKt.UserServiceCoroutineStub

    override suspend fun getUserById(userId: Long): User? {
        val request = GetUserRequest.newBuilder()
            .setId(userId)
            .build()

        val response = userServiceStub.getUser(request)

        return User(
            id = userId,
            name = response.name,
            role = response.role,
            gender = response.gender,
            email = response.email
        )
    }
}
