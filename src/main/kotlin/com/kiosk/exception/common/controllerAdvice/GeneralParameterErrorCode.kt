package com.kiosk.exception.common.controllerAdvice

import com.kiosk.exception.common.ErrorCode
import org.springframework.http.HttpStatus

enum class GeneralParameterErrorCode(
    override val httpStatus: HttpStatus,
    override val bizCode: Int,
    override val msg: String
) : ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, -999, "매개변수가 충분히 전달되지 못했거나 올바르지 않은 매개변수 값이 전달되었습니다.");

    companion object {
        val msgMap = values().associateBy(GeneralParameterErrorCode::msg)
    }

    override fun findMatchBizCode(failMessage: String?): Int {
        val first = msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }
        return if (first.isEmpty()) -999 else first[0]
    }
}
