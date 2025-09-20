package com.moimmee.gatheringservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GatheringServiceApplication

fun main(args: Array<String>) {
    runApplication<GatheringServiceApplication>(*args)
}
