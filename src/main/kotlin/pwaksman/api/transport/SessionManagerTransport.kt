package pwaksman.api.transport

import java.time.LocalDateTime

open class SessionManagerTransport {
    var id: Long? = null
    var sessionId: Long? = null
    var openAt: LocalDateTime? = null
    var duration: Long? = null
}