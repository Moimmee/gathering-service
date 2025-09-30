package com.moimmee.gatheringservice.infra.exception.handler

import com.moimmee.gatheringservice.infra.exception.CustomException
import com.moimmee.gatheringservice.infra.exception.error.GlobalErrorCode
import com.moimmee.gatheringservice.infra.exception.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.server.MethodNotAllowedException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException) = ErrorResponse.of(e)

    @ExceptionHandler(MethodNotAllowedException::class)
    fun handleMethodNotAllowedException(e: MethodNotAllowedException) =
        ErrorResponse.of(CustomException(GlobalErrorCode.METHOD_NOT_ALLOWED))

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException) =
        ErrorResponse.of(CustomException(GlobalErrorCode.METHOD_NOT_ALLOWED))

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException) =
        ErrorResponse.of(CustomException(GlobalErrorCode.HTTP_MESSAGE_NOT_READABLE))

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException) =
        ErrorResponse.of(CustomException(GlobalErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException) =
        ErrorResponse.of(CustomException(GlobalErrorCode.METHOD_ARGUMENT_NOT_VALID))

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(CustomException(GlobalErrorCode.INTERNAL_SERVER_ERROR, e.message.toString()))
    }
}