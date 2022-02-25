package com.kiosk.exception.common

import org.springframework.http.HttpStatus

open class BizException(code: ErrorCode) : RuntimeException() {
    override var message: String = code.msg
    var bizCode: Int = code.bizCode
    var httpStatus: HttpStatus = code.httpStatus
}