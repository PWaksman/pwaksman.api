package pwaksman.api.controller.v1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pwaksman.api.core.BaseController
import pwaksman.api.service.SessionVoteService
import pwaksman.api.transport.SessionVoteTransport

@RestController
@RequestMapping("/api/v1/SessionVote")
class SessionVoteController : BaseController<SessionVoteTransport, Long, SessionVoteService>() {
    @Autowired
    override lateinit var service: SessionVoteService
}