package pwaksman.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pwaksman.api.core.BaseService
import pwaksman.api.core.InvalidRequestData
import pwaksman.api.enumData.VoteType
import pwaksman.api.model.SessionVoteModel
import pwaksman.api.repository.SessionManagerRepository
import pwaksman.api.repository.SessionVoteRepository
import pwaksman.api.service.user.UserValidator
import pwaksman.api.transport.SessionVoteTransport
import java.time.LocalDateTime

@Service
class SessionVoteService : BaseService<SessionVoteTransport, SessionVoteModel, Long>() {

    @Autowired
    private lateinit var userValidator: UserValidator

    @Autowired
    private lateinit var sessionManagerRepository: SessionManagerRepository

    @Autowired
    override lateinit var repository: SessionVoteRepository

    override fun SessionVoteTransport.isValid() {
        if (this.sessionId == null) throw InvalidRequestData("Field 'sessionId' cannot be null")
        if (this.user == null) throw InvalidRequestData("Field 'user' cannot be null")
        if (this.vote == null) throw InvalidRequestData("Field 'vote' cannot be null")

        val vote: VoteType
        try {
            vote = VoteType.findByVote(this.vote!!)

            val userCanVote = userValidator.canVote(this.user!!)
            if (!userCanVote) throw Exception("Invalid user")
        } catch (ex: Exception) {
            throw InvalidRequestData(ex.message!!)
        }

        val userVote = repository.findBySessionIdAndUsr(this.sessionId!!, this.user!!)
        if (userVote != null) throw InvalidRequestData("User already voted")

        val session = sessionManagerRepository.findBySessionId(this.sessionId!!)
                ?: throw InvalidRequestData("The sessionId '${this.sessionId}' does not exist")

        val now = LocalDateTime.now()
        if (now < session.startAt || now > session.endAt) throw InvalidRequestData("Session date out of range")

        this.vote = vote.vote
    }

    override fun SessionVoteTransport.toModel(): SessionVoteModel {
        val model = SessionVoteModel()
        model.sessionId = this.sessionId
        model.usr = this.user
        model.vote = this.vote

        return model
    }

    override fun SessionVoteModel.toTransport(): SessionVoteTransport {
        val transport = SessionVoteTransport()
        transport.sessionId = this.sessionId
        transport.user = this.usr
        transport.vote = this.vote

        return transport
    }
}