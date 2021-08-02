package pwaksman.api.adapter

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import pwaksman.api.transport.SessionManagerTransport
import pwaksman.api.transport.v1.SessionManagerV1Transport

@Service
class SessionManagerTransportAdapter {

    @Value("\${sessionDuration}")
    private var sessionDuration: Long = 0

    fun toCurrent(v1: SessionManagerV1Transport): SessionManagerTransport {
        val current = SessionManagerTransport()
        current.id = v1.id
        current.sessionId = v1.sessionId
        current.openAt = v1.openAt
        current.duration = sessionDuration

        return current
    }

    fun toV1(current: SessionManagerTransport): SessionManagerV1Transport {
        val v1 = SessionManagerV1Transport()
        v1.id = current.id
        v1.sessionId = current.sessionId
        v1.openAt = current.openAt

        return v1
    }

}