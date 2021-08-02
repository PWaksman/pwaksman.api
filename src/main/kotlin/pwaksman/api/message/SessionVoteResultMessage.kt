package pwaksman.api.message

import pwaksman.api.transport.SessionVoteResultTransport

interface SessionVoteResultMessage {
    fun notify(result: SessionVoteResultTransport)
}