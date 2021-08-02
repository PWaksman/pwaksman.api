package pwaksman.api.controller.v1

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pwaksman.api.controller.v2.SessionVoteV2Controller

@RestController
@RequestMapping("/api/v1/SessionVote")
class SessionVoteV1Controller : SessionVoteV2Controller()