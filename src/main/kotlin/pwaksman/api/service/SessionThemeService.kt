package pwaksman.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pwaksman.api.core.BaseService
import pwaksman.api.model.SessionThemeModel
import pwaksman.api.repository.SessionThemeRepository
import pwaksman.api.transport.SessionThemeTransport

@Service
class SessionThemeService : BaseService<SessionThemeTransport, SessionThemeModel, Long>() {

    @Autowired
    override lateinit var repository: SessionThemeRepository

    override fun SessionThemeTransport.toModel(): SessionThemeModel {
        val model = SessionThemeModel()
        model.id = this.id
        model.description = this.description

        return model
    }

    override fun SessionThemeModel.toTransport(): SessionThemeTransport {
        val transport = SessionThemeTransport()
        transport.id = this.id
        transport.description = this.description

        return transport
    }
}