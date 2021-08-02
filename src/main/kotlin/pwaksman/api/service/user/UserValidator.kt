package pwaksman.api.service.user

interface UserValidator {
    fun canVote(user: String): Boolean
}