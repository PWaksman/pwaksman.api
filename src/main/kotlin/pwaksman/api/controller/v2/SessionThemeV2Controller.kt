package pwaksman.api.controller.v2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pwaksman.api.core.BaseController
import pwaksman.api.service.SessionThemeService
import pwaksman.api.transport.SessionThemeTransport

@RestController
@RequestMapping("/api/v2/SessionTheme")
open class SessionThemeV2Controller : BaseController<SessionThemeTransport, Long, SessionThemeService>() {

    @Autowired
    override lateinit var service: SessionThemeService
}