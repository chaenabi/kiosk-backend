package com.kiosk.exception

import org.springframework.validation.Errors

abstract class InvalidParameterException(val code: ErrorCode, val errors: Errors? = null) : BizException(code)