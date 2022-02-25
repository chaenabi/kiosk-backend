package com.kiosk.exception.customer

import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.common.controllerAdvice.GeneralParameterErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
enum class CustomerCrudErrorCode(
    override val httpStatus: HttpStatus,
    override val bizCode: Int,
    override val msg: String
) : ErrorCode {
    CUSTOMER_CRUD_FAIL(BAD_REQUEST, -1, "회원 관련 처리 요청이 실패했습니다."),
    CUSTOMER_ID_IS_NULL(BAD_REQUEST, -2, "회원 번호가 반드시 전달되어야 합니다."),
    CUSTOMER_ID_IS_EMPTY(BAD_REQUEST, -3, "회원 번호가 비어 있으면 안됩니다."),
    CUSTOMER_NOT_FOUND(NOT_FOUND, -4, "해당 회원은 존재하지 않습니다.");

    override fun findMatchBizCode(failMessage: String?): Int {
        val first = GeneralParameterErrorCode.msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }
        return if (first.isEmpty()) -999 else first[0]
    }
}