package pwaksman.api.message

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import pwaksman.api.transport.SessionVoteResultTransport

@Service
open class SessionEndMessage : SessionVoteResultMessage {
    @Value(value = "\${topic.name}")
    private lateinit var topic: String

    @Autowired
    private lateinit var template: KafkaTemplate<String, String>

    override fun notify(result: SessionVoteResultTransport) {
        val map = jacksonObjectMapper()
        val message = map.writeValueAsString(result)

        template.send(topic, message)
    }
}