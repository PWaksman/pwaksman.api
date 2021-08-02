package pwaksman.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import pwaksman.api.model.SessionThemeModel

interface SessionThemeRepository : JpaRepository<SessionThemeModel, Long>