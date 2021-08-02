package pwaksman.api.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "SessionTheme")
class SessionThemeModel : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = 1000)
    var description: String? = null
}