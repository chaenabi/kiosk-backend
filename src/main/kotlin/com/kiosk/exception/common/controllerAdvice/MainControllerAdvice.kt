package com.kiosk.exception.common.controllerAdvice

import com.kiosk.exception.common.BizException
import com.kiosk.exception.common.ErrorResponseDTO
import com.kiosk.exception.common.controllerAdvice.GeneralControllerAdvice.Companion.handleGeneralException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpServerErrorException.BadGateway
import org.springframework.web.client.HttpServerErrorException.ServiceUnavailable
import org.springframework.web.servlet.NoHandlerFoundException
import java.net.MalformedURLException

@RestControllerAdvice
class MainControllerAdvice {

    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * 예상하지 못한, 예외처리하지 못한 코드에서
     * 에러가 발생할 때 동작합니다.
     *
     * @param e 전역 에러 핸들링
     * @return 500
     */
    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception): ResponseEntity<ErrorResponseDTO> {
        return handleGeneralException(HttpStatus.INTERNAL_SERVER_ERROR, e)
    }

    /**
     * - @Valid 에서 모든 파라미터가 비어있을경우 발생합니다.
     *
     * @return 404
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun catchIllegalArgumentException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponseDTO> {
        // 직접 메시지 커스텀
        val errorResponseDTO = ErrorResponseDTO(
            message = "필수 항목이 모두 비어있습니다.",
            httpStatus = HttpStatus.NOT_FOUND,
            errorCode = HttpStatus.NOT_FOUND.value()
        )
        log.error(e.message)
        return ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.NOT_FOUND)
    }

    /**
     * 잘못된 인수로 요청이 왔을 경우 처리합니다.
     *
     *
     * 예시: StrangeProtocol://www.homepage.com/...
     * 예시: http://www.homepage.com?data={HackingAttack}
     *
     *
     * IllegalArgumentException과 MalformedURLException은 한 묶음입니다.
     *
     * @return 400
     */
    @ExceptionHandler(IllegalArgumentException::class)
    protected fun handleIllgegalURLException(iae: IllegalArgumentException): ResponseEntity<ErrorResponseDTO> {
        return handleGeneralException(HttpStatus.BAD_REQUEST, iae)
    }

    @ExceptionHandler(MalformedURLException::class)
    protected fun handleMalformedURLException(mue: MalformedURLException): ResponseEntity<ErrorResponseDTO> {
        return handleGeneralException(HttpStatus.BAD_REQUEST, mue)
    }

    /**
     * 404 에러가 발생할 경우 기본 지정된 에러페이지로 리다이렉트시킵니다.
     *
     * @return 404 뷰페이지
     */
    @ExceptionHandler(NoHandlerFoundException::class)
    protected fun handle404(nhfe: NoHandlerFoundException): ResponseEntity<ErrorResponseDTO> {
        log.error("어떤 유저가 존재하지 않는 url로 자원을 요청했습니다.")
        return handleGeneralException(HttpStatus.NOT_FOUND, nhfe)
    }

    /**
     * 이 서버에서 지원하지 않는 http mapping method로
     * 요청이 왔을 경우 동작합니다.
     * (예시: get만 지원하는 특정 메서드를 클라이언트가 post 방식으로 요청할 경우 동작)
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return 405
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): ResponseEntity<ErrorResponseDTO> {
        return handleGeneralException(HttpStatus.METHOD_NOT_ALLOWED, e)
    }

    /**
     * 프로토콜 비정상 에러 처리
     * 잘못된 프로토콜 연결을 시도했거나 프로토콜 연결에 장애가 발생했을 때 동작합니다.
     *
     * @param e BadGateway 502
     * @return 502
     */
    @ExceptionHandler(BadGateway::class)
    protected fun handleBadGateway(e: BadGateway): ResponseEntity<ErrorResponseDTO> {
        return handleGeneralException(HttpStatus.BAD_GATEWAY, e)
    }

    /**
     * 서버 접속자가 많아서 서버가 처리하지 못하고 있거나
     * 서버에 장애가 발생하여 요청을 정상 처리하지 못할 경우 동작합니다.
     *
     * @param e Service Unavailable 503
     * @return 503
     */
    @ExceptionHandler(ServiceUnavailable::class)
    protected fun handleServiceUnavailable(e: ServiceUnavailable): ResponseEntity<ErrorResponseDTO> {
        return handleGeneralException(HttpStatus.SERVICE_UNAVAILABLE, e)
    }

    /**
     * () -> throw new BizException(e)
     * 등의 방식으로 작성하면, 이 메서드가 이 예외처리에 대한 에러를 핸들링하게 됩니다.
     *
     * @param e throw new BizException(e)에서의 e
     * @return new BizException(e)에서 생성자로 전달된 e의 정보를 반환
     */
    @ExceptionHandler(BizException::class)
    fun catchBizException(e: BizException): ResponseEntity<ErrorResponseDTO> {
        return handleGeneralException(e.httpStatus, e)
    }
}