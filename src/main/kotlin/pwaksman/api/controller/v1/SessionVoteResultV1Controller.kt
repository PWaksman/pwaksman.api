package pwaksman.api.controller.v1

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pwaksman.api.controller.v2.SessionVoteResultV2Controller

@RestController
@RequestMapping("/api/v1/SessionVoteResult")
class SessionVoteResultV1Controller : SessionVoteResultV2Controller()