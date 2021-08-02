package pwaksman.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import pwaksman.api.model.SessionManagerModel

interface SessionManagerRepository : JpaRepository<SessionManagerModel, Long> {
    fun findBySessionId(sessionId: Long): SessionManagerModel?
}