package com.kiosk.exception.store

import com.kiosk.exception.common.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND

enum class StoreCrudErrorCode(
    override val httpStatus: HttpStatus,
    override val bizCode: Int,
    override val msg: String
) : ErrorCode {
    STORE_CRUD_FAIL(BAD_REQUEST, -1, "지점 관련 처리 요청이 실패했습니다."),
    STORE_ID_IS_NULL(BAD_REQUEST, -2, "지점 번호가 반드시 전달되어야 합니다."),
    STORE_OWNER_IS_NULL(BAD_REQUEST, -2, "지점 주인 이름 정보는 필수입니다."),
    STORE_NOT_FOUND(NOT_FOUND, -3, "해당 지점은 존재하지 않습니다.");

    companion object {
        val msgMap = values().associateBy(StoreCrudErrorCode::msg)
    }

    override fun findMatchBizCode(failMessage: String?): Int {
        val first = msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }
        println(first)
        return if (first.isEmpty()) -999 else first[0]
    }
}