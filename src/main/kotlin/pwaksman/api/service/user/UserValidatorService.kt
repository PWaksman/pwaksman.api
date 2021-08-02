package pwaksman.api.service.user

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.HttpURLConnection
import java.net.URL

@Service
class UserValidatorService : UserValidator {

    @Value("\${cpfValidatorServer}")
    private lateinit var validatorServer: String

    override fun canVote(user: String) = try {
        val url = URL("$validatorServer$user")
        val con = url.openConnection() as HttpURLConnection

        var result = ""
        con.inputStream.bufferedReader().useLines {
            result = it.first()
        }

        val map = jacksonObjectMapper()
        val requestResult = map.readValue<ValidatorResult>(result)

        requestResult.status!!.equals("ABLE_TO_VOTE", ignoreCase = true)
    } catch (ex: Exception) {
        println("Unable to validate user '$user'")
        ex.printStackTrace()

        false
    }
}