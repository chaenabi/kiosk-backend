package com.kiosk.exception.common

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import java.time.LocalDateTime
import java.util.*

class ErrorResponseDTO(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty("timestamp")
    val now: LocalDateTime = LocalDateTime.now(),
    val message: String,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("code")
    val errorCode: Int?,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("status")
    val httpStatus: HttpStatus,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("errors")
    var customFieldErrors: List<CustomFieldError> = Collections.emptyList()
) {
    fun errors(errors: Errors, errorCode: ErrorCode): ErrorResponseDTO {
        setCustomFieldErrors(errors.fieldErrors, errorCode)
        return this
    }

    /**
     * BindingResult.getFieldErrors() 메소드를 통해 전달받은 fieldErrors 및 Enumerate errorCode
     */
    private fun setCustomFieldErrors(fieldErrors: List<FieldError>, errorCode: ErrorCode) {
        customFieldErrors = arrayListOf()
        for (fieldError in fieldErrors) {
            (customFieldErrors as ArrayList<CustomFieldError>).add(
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
     * # @Valid 또는 @Validated 에 의한 parameter 검증에 통과하지 못한 필드가 담긴 클래스.
     */
    class CustomFieldError(
        private val rejectedParameter: String? = null,
        private val rejectedValue: Any? = null,
        private val reason: String? = null,
        private val internalCode: Int? = null
    )
}