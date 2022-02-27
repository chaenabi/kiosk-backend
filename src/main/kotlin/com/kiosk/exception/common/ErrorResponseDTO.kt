package com.kiosk.exception.common

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import java.time.LocalDateTime
import java.util.*

class ErrorResponseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("timestamp")
    private var now: String? = null
    private var message: String?

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("code")
    private var errorCode: Int?

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("status")
    private var httpStatus: HttpStatus

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("errors")
    private var customFieldErrors: List<CustomFieldError>

    private constructor(builder: ErrorResponseDTOBuilder) {
        if (builder.displayNow) now = LocalDateTime.now().toString().replace('T', ' ').split(".")[0]
        message = builder.message
        errorCode = builder.errorCode
        httpStatus = builder.httpStatus
        customFieldErrors = builder.customFieldErrors
    }
    companion object {
        fun builder(): ErrorResponseDTOBuilder {
            return ErrorResponseDTOBuilder()
        }
    }

    class ErrorResponseDTOBuilder {
        var displayNow = true
        lateinit var message: String
        var errorCode: Int? = null
        lateinit var httpStatus: HttpStatus
        var customFieldErrors: MutableList<CustomFieldError> = Collections.emptyList()

        fun errorCode(code: Int): ErrorResponseDTOBuilder {
            errorCode = code
            return this
        }

        fun httpStatus(status: HttpStatus): ErrorResponseDTOBuilder {
            httpStatus = status
            return this
        }

        fun message(message: String): ErrorResponseDTOBuilder {
            this.message = message
            return this
        }

        /**
         * errors 메서드는 (@Valid 또는 @Validated)와 BindingResult 를 사용할 때만 사용해야 합니다.
         * errorCode를 설정하면 미리 협의된 에러코드를 함꼐 클라이언트에 전달할 수 있습니다.
         * (오버로딩 메서드를 중복으로 사용할 경우) 가장 마지막에 호출된 설정 정보로 적용됩니다.
         *
         * @param errors BindingResult.getFieldErrors() 메소드를 통해 전달받은 fieldErrors
         * @return 빌더 메서드 체이닝 ErrorResponseDTO
         */
        fun errors(errors: Errors, errorCode: ErrorCode): ErrorResponseDTOBuilder {
            setCustomFieldErrors(errors.fieldErrors, errorCode)
            return this
        }

        /**
         * BindingResult.getFieldErrors() 메소드를 통해 전달받은 fieldErrors 및 Enumerate errorCode
         */
        private fun setCustomFieldErrors(fieldErrors: List<FieldError>, errorCode: ErrorCode) {
            customFieldErrors = ArrayList()
            for (fieldError in fieldErrors) {
                customFieldErrors.add(
                    CustomFieldError(
                        fieldError.codes?.get(0)?.split("\\.")!![2],
                        fieldError.rejectedValue,
                        fieldError.defaultMessage,
                        errorCode.findMatchBizCode(fieldError.defaultMessage)
                    )
                )
            }
        }

        /**
         * now (timestamp) 출력을 비활성화합니다.
         *
         * @return now (timestamp) false
         */
        fun offDisplayTimeStamp(): ErrorResponseDTOBuilder {
            displayNow = false
            return this
        }

        fun build(): ErrorResponseDTO {
            return ErrorResponseDTO(this)
        }
    }

    /**
     * # @Valid 또는 @Validated 에 의한 parameter 검증에 통과하지 못한 필드가 담긴 클래스.
     */
    class CustomFieldError(
        private val rejectedParameter: String? = null,
        private val rejectedValue: Any? = null,
        private val reason: String? = null,
        private val internalCode: Int? = null
    )
}