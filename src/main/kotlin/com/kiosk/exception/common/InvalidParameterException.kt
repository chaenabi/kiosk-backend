package com.kiosk.exception.common

import org.springframework.validation.Errors

abstract class InvalidParameterException(val errorCode: ErrorCode, val errors: Errors) : BizException(errorCode)