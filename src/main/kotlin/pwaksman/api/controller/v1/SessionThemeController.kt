package pwaksman.api.controller.v1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pwaksman.api.core.BaseController
import pwaksman.api.service.SessionThemeService
import pwaksman.api.transport.SessionThemeTransport

@RestController
@RequestMapping("/api/v1/SessionTheme")
class SessionThemeController : BaseController<SessionThemeTransport, Long, SessionThemeService>() {

    @Autowired
    override lateinit var service: SessionThemeService
}