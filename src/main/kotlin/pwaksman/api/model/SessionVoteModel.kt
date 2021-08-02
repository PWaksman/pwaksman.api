package pwaksman.api.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "SessionVote")
class SessionVoteModel : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var sessionId: Long? = null

    @Column(length = 11)
    var usr: String? = null

    @Column(length = 3)
    var vote: String? = null
}