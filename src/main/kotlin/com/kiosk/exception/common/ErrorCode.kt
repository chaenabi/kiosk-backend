package com.kiosk.exception.common

import org.springframework.http.HttpStatus

interface ErrorCode {
    val httpStatus: HttpStatus
    val bizCode: Int
    val msg: String

    fun findMatchBizCode(failMessage: String?): Int
}