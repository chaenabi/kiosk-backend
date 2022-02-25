package com.kiosk.exception.controllerAdvice

import com.kiosk.exception.ErrorCode
import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
enum class GeneralParameterErrorCode(
    override val httpStatus: HttpStatus,
    override val bizCode: Int,
    override val msg: String
) : ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, -999, "매개변수가 충분히 전달되지 못했거나 올바르지 않은 매개변수 값이 전달되었습니다.");

    override fun findMatchBizCode(failMessage: String?): Int {
        return values()
            .filter { msg == failMessage }
            .map(GeneralParameterErrorCode::bizCode)
            .first()
    }
}
