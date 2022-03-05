package com.kiosk.exception.order

import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.common.InvalidParameterException
import org.springframework.validation.Errors

class InvalidOrderParameterException(errors: Errors, errorCode: ErrorCode): InvalidParameterException(errorCode, errors)