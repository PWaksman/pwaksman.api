package pwaksman.api.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import pwaksman.api.core.DataNotFound
import pwaksman.api.core.InvalidRequestData

@ControllerAdvice
class RestErrorController : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [DataNotFound::class])
    fun dataNotFound(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ex.message!!, HttpHeaders(), HttpStatus.CONFLICT, request)
    }

    @ExceptionHandler(value = [InvalidRequestData::class])
    fun invalidRequestData(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ex.message!!, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}