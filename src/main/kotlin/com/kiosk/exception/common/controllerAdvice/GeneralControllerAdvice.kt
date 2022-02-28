package com.kiosk.exception.common.controllerAdvice

import com.kiosk.exception.common.BizException
import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.common.ErrorResponseDTO
import com.kiosk.exception.common.InvalidParameterException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

class GeneralControllerAdvice {

    companion object {
        fun handleGeneralException(httpStatus: HttpStatus, e: Exception, internalCode: Int = -999): ResponseEntity<ErrorResponseDTO> {
            val response= ErrorResponseDTO(
                errorCode = httpStatus.value(),
                httpStatus = httpStatus,
                message = e.message ?: httpStatus.reasonPhrase,
                internalCode = internalCode
            )
            return ResponseEntity<ErrorResponseDTO>(response, getHttpHeader(), httpStatus)
        }

        /**
         * 정형화된 에러 응답메시지 포맷을 생성합니다.
         * 이 메서드는 @Valid 검증을 통해 BindingResult 정보를 가져오는 익셉션을 위해 사용
         *
         * @param httpStatus 발생한 에러
         * @param e          @Valid 또는 @Validated 검증을 하는 익셉션 목록
         * @return ResponseEntity<ErrorResponseDTO>
        </ErrorResponseDTO> */
        fun handleInvalidParameterException(httpStatus: HttpStatus, errorCode: ErrorCode, e: InvalidParameterException): ResponseEntity<ErrorResponseDTO> {
            val response: ErrorResponseDTO = ErrorResponseDTO(
                errorCode = httpStatus.value(),
                httpStatus = httpStatus,
                message = e.message,
                internalCode = e.bizCode
            ).errors(e.errors, errorCode)
            return ResponseEntity<ErrorResponseDTO>(response, getHttpHeader(), httpStatus)
        }

        /**
         * 클라이언트에게 전달할 에러 정보 헤더를 설정합니다.
         *
         * @return 에러 응답 헤더
         */
        private fun getHttpHeader(): HttpHeaders? {
            val responseHeaders = HttpHeaders()
            responseHeaders.add("Content-Type", MediaType.APPLICATION_JSON.toString() + ";charset=UTF-8")
            return responseHeaders
        }
    }

}