package com.kiosk.exception.common

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("internalCode")
    val internalCode: Int? = null,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("errors")
    var customFieldErrors: MutableList<CustomFieldError> = arrayListOf()
) {

    fun errors(errors: Errors, errorCode: ErrorCode): ErrorResponseDTO {
        setCustomFieldErrors(errors.fieldErrors, errorCode)
        return this
    }

    /**
     * BindingResult.getFieldErrors() 메소드를 통해 전달받은 fieldErrors 및 Enumerate errorCode
     */
    private fun setCustomFieldErrors(fieldErrors: List<FieldError>, errorCode: ErrorCode) {
        for (fieldError in fieldErrors) {
            customFieldErrors.add(
                CustomFieldError(
                    fieldError.codes?.get(0)?.split(".")!![2],
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
        val rejectedParameter: String? = null,
        val rejectedValue: Any? = null,
        val reason: String? = null,
        val internalCode: Int? = null
    )
}