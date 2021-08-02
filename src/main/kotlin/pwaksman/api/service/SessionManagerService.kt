package pwaksman.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pwaksman.api.core.BaseService
import pwaksman.api.core.InvalidRequestData
import pwaksman.api.model.SessionManagerModel
import pwaksman.api.repository.SessionManagerRepository
import pwaksman.api.repository.SessionThemeRepository
import pwaksman.api.transport.SessionManagerTransport

@Service
class SessionManagerService : BaseService<SessionManagerTransport, SessionManagerModel, Long>() {

    @Autowired
    private lateinit var sessionRepository: SessionThemeRepository

    @Autowired
    override lateinit var repository: SessionManagerRepository

    override fun SessionManagerTransport.isValid() {
        if (this.sessionId == null) throw InvalidRequestData("Field 'sessionId' cannot be null")
        if (this.openAt == null) throw InvalidRequestData("Field 'openAt' cannot be null")

        val sessionTheme = sessionRepository.findById(this.sessionId!!)
        if (!sessionTheme.isPresent) throw InvalidRequestData("The sessionId '${this.sessionId}' does not exist")

        val sessionExists = repository.findBySessionId(this.sessionId!!)
        if (sessionExists != null) throw InvalidRequestData("The sessionId '${this.sessionId}' already exist")
    }

    override fun SessionManagerTransport.toModel(): SessionManagerModel {
        val model = SessionManagerModel()
        model.sessionId = this.sessionId
        model.startAt = this.openAt
        model.endAt = this.openAt!!.plusHours(1)

        return model
    }

    override fun SessionManagerModel.toTransport(): SessionManagerTransport {
        val transport = SessionManagerTransport()
        transport.id = this.id
        transport.sessionId = this.sessionId
        transport.openAt = this.startAt

        return transport
    }
}