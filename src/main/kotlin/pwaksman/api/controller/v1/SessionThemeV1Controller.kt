package pwaksman.api.controller.v1

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pwaksman.api.controller.v2.SessionThemeV2Controller

@RestController
@RequestMapping("/api/v1/SessionTheme")
class SessionThemeV1Controller : SessionThemeV2Controller()