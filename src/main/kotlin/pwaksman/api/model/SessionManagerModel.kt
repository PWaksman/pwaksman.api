package pwaksman.api.model

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "SessionManager")
class SessionManagerModel : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var sessionId: Long? = null
    var startAt: LocalDateTime? = null
    var endAt: LocalDateTime? = null
}