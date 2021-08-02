package pwaksman.api.enumData

import java.security.InvalidParameterException

enum class VoteType(val vote: String) {
    Yes("Sim"),
    No("Não"),
    ;

    companion object {
        fun findByVote(vote: String): VoteType {
            for (enum in values())
                if (enum.vote.equals(vote, ignoreCase = true))
                    return enum

            throw InvalidParameterException("Invalid vote type: $vote")
        }
    }
}