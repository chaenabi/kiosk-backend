package com.kiosk.api.common

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

class ResponseDTO<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var data: T? = null
    val message: String
    val httpStatus: HttpStatus

    constructor(message: SuccessMessage, httpStatus: HttpStatus) {
        this.message = message.getMsg()
        this.httpStatus = httpStatus
    }

    constructor(data: T, message: SuccessMessage, httpStatus: HttpStatus) {
        this.data = data
        this.message = message.getMsg()
        this.httpStatus = httpStatus
    }

}