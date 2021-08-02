package pwaksman.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import pwaksman.api.model.SessionVoteModel

interface SessionVoteRepository : JpaRepository<SessionVoteModel, Long> {
    fun findBySessionIdAndUsr(sessionId: Long, usr: String): SessionVoteModel?
}