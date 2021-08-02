package pwaksman.api.controller.v1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pwaksman.api.service.SessionVoteResultService
import pwaksman.api.transport.SessionVoteResultTransport

@RestController
@RequestMapping("/api/v1/SessionVoteResult")
class SessionVoteResultController {

    @Autowired
    private lateinit var resultService: SessionVoteResultService

    @GetMapping("{id}")
    fun sessionVoteResult(@PathVariable("id") sessionId: Long): SessionVoteResultTransport {
        return resultService.result(sessionId)
    }

}