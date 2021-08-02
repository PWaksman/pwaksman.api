package pwaksman.api.controller.v2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pwaksman.api.core.BaseController
import pwaksman.api.service.SessionManagerService
import pwaksman.api.transport.SessionManagerTransport

@RestController
@RequestMapping("/api/v2/SessionManager")
open class SessionManagerV2Controller : BaseController<SessionManagerTransport, Long, SessionManagerService>() {
    @Autowired
    override lateinit var service: SessionManagerService
}