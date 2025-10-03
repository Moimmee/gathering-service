package com.moimmee.gatheringservice.domain.party.presentation.contorller

import com.moimmee.gatheringservice.domain.party.application.service.PartyParticipantService
import com.moimmee.gatheringservice.domain.party.application.service.PartyService
import com.moimmee.gatheringservice.domain.party.domain.enums.PartyCategory
import com.moimmee.gatheringservice.domain.party.presentation.dto.request.CreatePartyRequest
import com.moimmee.gatheringservice.domain.party.presentation.dto.response.PartyResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/parties")
@RestController
class PartyController(
    private val partyService: PartyService,
    private val partyParticipantService: PartyParticipantService,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createParty(@RequestBody request: CreatePartyRequest) = partyService.createParty(request)

    @GetMapping
    fun getParties(
        @RequestParam("latitude") latitude: Double,
        @RequestParam("longitude") longitude: Double,
        @RequestParam("category") category: PartyCategory?,
    ) = partyService.getParties(latitude, longitude, category)

    @GetMapping("/{partyId}")
    fun getParty(@PathVariable partyId: UUID): PartyResponse = partyService.getParty(partyId)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{partyId}/join")
    fun joinParty(@PathVariable partyId: UUID): Unit = partyParticipantService.joinParty(partyId)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{partyId}/leave")
    fun leaveParty(@PathVariable partyId: UUID): Unit = partyParticipantService.leaveParty(partyId)
}