package com.kiosk.exception.controllerAdvice

import com.kiosk.exception.ErrorResponseDTO
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.util.*

@Slf4j
class GeneralControllerAdvice {

    companion object {
        fun handleGeneralException(httpStatus: HttpStatus, e: Exception): ResponseEntity<ErrorResponseDTO> {
            val response: ErrorResponseDTO = ErrorResponseDTO.builder()
                .errorCode(httpStatus.value())
                .httpStatus(httpStatus)
                .message(
                    Arrays.stream(e)
                        .filter { obj: Exception? ->
                            Objects.nonNull(
                                obj
                            )
                        }.findFirst()
                        .map { obj: Exception -> obj.message }
                        .orElse(httpStatus.reasonPhrase))
                .build()
            //log.error(response.getMessage())
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
        fun handleValidParameterException(
            httpStatus: HttpStatus,
            errorCode: ErrorCode?,
            e: InvalidParameterException?
        ): ResponseEntity<ErrorResponseDTO?>? {
            val response: ErrorResponseDTO = ErrorResponseDTO.builder()
                .errorCode(httpStatus.value())
                .httpStatus(httpStatus)
                .message(
                    Arrays.stream(e)
                        .filter { obj: Any? -> Objects.nonNull(obj) }.findFirst()
                        .map { obj: Exception -> obj.message }
                        .orElse(httpStatus.toString()))
                .errors(
                    Arrays.stream(e)
                        .filter { obj: Any? -> Objects.nonNull(obj) }
                        .findFirst()
                        .orElseThrow { BizException(GeneralParameterErrorCode.INVALID_PARAMETER) }
                        .getErrors(), errorCode)
                .build()
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