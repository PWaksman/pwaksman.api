package pwaksman.api.transport

class SessionVoteResultTransport {
    var sessionId: Long? = null
    var description: String? = null
    var voteResult = HashMap<String, Long>()
}