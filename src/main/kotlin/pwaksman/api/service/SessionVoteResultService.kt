package pwaksman.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pwaksman.api.core.InvalidRequestData
import pwaksman.api.message.SessionVoteResultMessage
import pwaksman.api.model.SessionVoteModel
import pwaksman.api.repository.SessionManagerRepository
import pwaksman.api.repository.SessionThemeRepository
import pwaksman.api.transport.SessionVoteResultTransport
import java.time.LocalDateTime
import javax.persistence.EntityManager
import javax.persistence.Tuple

@Service
class SessionVoteResultService {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Autowired
    private lateinit var sessionThemeRepository: SessionThemeRepository

    @Autowired
    private lateinit var sessionMenegerRepository: SessionManagerRepository

    @Autowired
    private lateinit var resultMessageNotification: SessionVoteResultMessage

    fun result(sessionId: Long): SessionVoteResultTransport {
        val sessionManager = sessionMenegerRepository.findBySessionId(sessionId)
                ?: throw InvalidRequestData("Session not started")

        val now = LocalDateTime.now()
        if (now < sessionManager.endAt) throw InvalidRequestData("Session in progress")

        val themeExist = sessionThemeRepository.findById(sessionId)
        if (!themeExist.isPresent) throw InvalidRequestData("Session not found")

        val builder = entityManager.criteriaBuilder
        val query = builder.createQuery(Tuple::class.java)
        val fromVoteSession = query.from(SessionVoteModel::class.java)
        query.where(builder.equal(fromVoteSession.get<Long>("sessionId"), sessionId))
        query.multiselect(fromVoteSession.get<String>("vote"), builder.count(fromVoteSession))
        query.groupBy(fromVoteSession.get<String>("vote"))

        val voteResult = SessionVoteResultTransport()
        voteResult.sessionId = sessionId
        voteResult.description = themeExist.get().description

        val result = entityManager.createQuery(query).resultList
        for (record in result)
            voteResult.voteResult[record[0] as String] = record[1] as Long

        resultMessageNotification.notify(voteResult)

        return voteResult
    }
}