package pwaksman.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import pwaksman.api.core.BaseService
import pwaksman.api.core.InvalidRequestData
import pwaksman.api.model.SessionManagerModel
import pwaksman.api.repository.SessionManagerRepository
import pwaksman.api.repository.SessionThemeRepository
import pwaksman.api.transport.SessionManagerTransport
import java.time.Duration

@Service
class SessionManagerService : BaseService<SessionManagerTransport, SessionManagerModel, Long>() {

    @Value("\${sessionDuration}")
    private var sessionDuration: Long = 0

    @Autowired
    private lateinit var sessionRepository: SessionThemeRepository

    @Autowired
    public override lateinit var repository: SessionManagerRepository

    override fun SessionManagerTransport.isValid() {
        if (this.sessionId == null) throw InvalidRequestData("Field 'sessionId' cannot be null")
        if (this.openAt == null) throw InvalidRequestData("Field 'openAt' cannot be null")

        if (this.duration == null) this.duration = sessionDuration
        if (this.duration!! <= 0) throw InvalidRequestData("Field 'duration' cannot be less or equal to zero")

        val sessionTheme = sessionRepository.findById(this.sessionId!!)
        if (!sessionTheme.isPresent) throw InvalidRequestData("The sessionId '${this.sessionId}' does not exist")

        val sessionExists = repository.findBySessionId(this.sessionId!!)
        if (sessionExists != null) throw InvalidRequestData("The sessionId '${this.sessionId}' already exist")
    }

    override fun SessionManagerTransport.toModel(): SessionManagerModel {
        val model = SessionManagerModel()
        model.sessionId = this.sessionId
        model.startAt = this.openAt
        model.endAt = this.openAt!!.plusSeconds(this.duration!!)

        return model
    }

    override fun SessionManagerModel.toTransport(): SessionManagerTransport {
        val transport = SessionManagerTransport()
        transport.id = this.id
        transport.sessionId = this.sessionId
        transport.openAt = this.startAt

        val duration = Duration.between(this.startAt!!, this.endAt!!)
        transport.duration = duration.seconds

        return transport
    }
}