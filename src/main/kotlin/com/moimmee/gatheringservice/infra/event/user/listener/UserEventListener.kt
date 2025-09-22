package com.moimmee.gatheringservice.infra.event.user.listener

import com.moimmee.gatheringservice.infra.event.user.response.UserCreatedEvent
import com.moimmee.gatheringservice.logger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class UserEventListener {
    private val log = logger()

    @KafkaListener(topics = ["user-events"])
    fun handleUserCreated(
        @Payload event: UserCreatedEvent,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Header(KafkaHeaders.OFFSET) offset: Long
    ) {
        log.info("사용자 생성 이벤트 수신: $event (토픽: $topic, 파티션: $partition, 오프셋: $offset)")

        // 비즈니스 로직 처리
        processUserCreated(event)
    }

    private fun processUserCreated(event: UserCreatedEvent) {
        // 예: 다른 서비스의 사용자 정보 동기화
        // 예: 환영 이메일 발송
        // 예: 사용자 통계 업데이트
    }
}