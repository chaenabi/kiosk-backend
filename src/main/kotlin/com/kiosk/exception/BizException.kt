package com.kiosk.exception

import org.springframework.http.HttpStatus

open class BizException(code: ErrorCode) : RuntimeException() {
    override var message: String? = null
    private var bizCode: Int? = null
    private var httpStatus: HttpStatus? = null

    init {
        message = code.getMsg()
        bizCode = code.getBizCode()
        httpStatus = code.getHttpStatus()
    }
}