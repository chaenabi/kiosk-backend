package com.kiosk.exception.customer

import com.kiosk.exception.common.ErrorResponseDTO
import com.kiosk.exception.common.controllerAdvice.GeneralControllerAdvice.Companion.handleInvalidParameterException
import com.kiosk.exception.customer.InvalidCustomerParameterException
import org.springframework.core.Ordered.HIGHEST_PRECEDENCE
import org.springframework.core.Ordered.LOWEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver

@RestControllerAdvice
class CustomerControllerAdvice {

    /**
     * # @Valid 또는 @Validated 애너테이션을 통한 검증 실패시 동작합니다.
     * # @Valid 또는 @Validated 애너테이션을 사용하는 컨트롤러 메서드에 대한 예외 응답 처리시
     * errors 인자값을 설정하면, 보다 자세한 에러메시지를 클라이언트에 전달할 수 있습니다.
     *
     * @param e InvalidParameterException
     * @return 400 (Bad Request: invalid parameter error)
     * @see ErrorResponseDTO
     */
    @ExceptionHandler(InvalidCustomerParameterException::class)
    protected fun handleInvalidPostParameterException(e: InvalidCustomerParameterException): ResponseEntity<ErrorResponseDTO> {
        return handleInvalidParameterException(e.httpStatus, e.errorCode, e)
    }
}