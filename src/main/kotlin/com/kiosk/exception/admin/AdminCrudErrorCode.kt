package com.kiosk.exception.admin

import com.kiosk.exception.common.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND

enum class AdminCrudErrorCode(
    override val httpStatus: HttpStatus,
    override val bizCode: Int,
    override val msg: String
) : ErrorCode {
    STORE_CRUD_FAIL(BAD_REQUEST, -1, "관리자 관련 처리 요청이 실패했습니다."),
    ADMIN_ID_IS_NULL(BAD_REQUEST, -2, "관리자 번호가 반드시 전달되어야 합니다."),
    ADMIN_NAME_IS_NULL(BAD_REQUEST, -3, "관리자 아이디가 반드시 전달되어야 합니다."),
    ADMIN_PASSWORD_IS_NULL(BAD_REQUEST, -4, "관리자 비밀번호가 전달되지 않았습니다."),
    ADMIN_NOT_FOUND(NOT_FOUND, -5, "존재하지 않는 관리자 아이디입니다."),
    ADMIN_PASSWORD_IS_INVALID(NOT_FOUND, -6, "관리자 비밀번호가 틀립니다.");

    companion object {
        val msgMap = values().associateBy(AdminCrudErrorCode::msg)
    }

    override fun findMatchBizCode(failMessage: String?): Int {
        val first = msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }
        println(first)
        return if (first.isEmpty()) -999 else first[0]
    }
}