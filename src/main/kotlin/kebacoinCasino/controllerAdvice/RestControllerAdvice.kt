package kebacoinCasino.controllerAdvice

import kebacoinCasino.exception.*
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.http.HttpStatus
import org.hibernate.validator.internal.engine.path.PathImpl
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.ConstraintViolationException
import kotlin.io.AccessDeniedException

@RestControllerAdvice(annotations = [RestController::class])
class RestControllerAdvice{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [(ConstraintViolationException::class)])
    fun handleConstraintViolationException(cve: ConstraintViolationException): ErrorDto {
        val validationErrorDto = ErrorDto()
        for (violation in cve.constraintViolations) {
            var key = "error"
            if (violation.propertyPath != null && (violation.propertyPath as PathImpl)
                    .leafNode.name != null
            ) {
                key = (violation.propertyPath as PathImpl).leafNode.name
            }
            validationErrorDto.errors.add(ErrorField(key, violation.message))
        }
        return validationErrorDto
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [(IllegalBetException::class)])
    fun handleIllegalBetException(ibe:IllegalBetException):ErrorDto{
        val errorDto=ErrorDto()
        errorDto.errors.add(ErrorField("error","Not a valid bet!"))
        return errorDto
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [(WrongTableException::class)])
    fun handleWrongTableException(wte:WrongTableException):ErrorDto{
        val errorDto=ErrorDto()
        errorDto.errors.add(ErrorField("error","You can't use this machine or table!"))
        return errorDto
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [(LowBalanceException::class)])
    fun handleLowBalanceException(lbe:LowBalanceException):ErrorDto{
        val errorDto=ErrorDto()
        errorDto.errors.add(ErrorField("error","Your balance is too low for this transaction!"))
        return errorDto
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [(IllegalMessageException::class)])
    fun handleBadRequests(ime:IllegalMessageException):ErrorDto{
        val errorDto=ErrorDto()
        errorDto.errors.add(ErrorField("requesterror","This message format for the request is not enabled!"))
        return errorDto
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = [(TokenExpiredException::class)])
    fun handleTokenExpiredException(tee:TokenExpiredException):ErrorDto{
        val errorDto = ErrorDto()
        errorDto.errors.add(ErrorField("tokenerror", "The token had expired!"))
        return errorDto
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [(UserNotFoundException::class)])
    fun handleUserNotFoundException(unfe: UserNotFoundException): ErrorDto {
        val errorDto = ErrorDto()
        errorDto.errors.add(ErrorField("entityerror", "The requested user does not exist!"))
        return errorDto
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [(UsernameAlreadyExistsException::class)])
    fun handleUsernameAlreadyExistsException(unfe: UsernameAlreadyExistsException): ErrorDto {
        val errorDto = ErrorDto()
        errorDto.errors.add(ErrorField("entityerror", "The requested username already exists!"))
        return errorDto
    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = [(Exception::class)])
//    fun handleAllOtherExceptions(e: Exception): ErrorDto {
//        val errorDto = ErrorDto()
//        errorDto.errors.add(ErrorField("server error", "Something went wrong!"))
//        return errorDto
//    }

    inner class ErrorDto{
        val errors:MutableList<ErrorField> = mutableListOf()
    }

    inner class ErrorField(val name:String,val message:String)
}