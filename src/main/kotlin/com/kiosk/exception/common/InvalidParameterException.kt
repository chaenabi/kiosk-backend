package com.kiosk.exception.common

import org.springframework.validation.Errors

abstract class InvalidParameterException(val code: ErrorCode, val errors: Errors) : BizException(code)