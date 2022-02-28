package com.kiosk.exception.item

import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.common.InvalidParameterException
import org.springframework.validation.Errors

class InvalidItemParameterException(errors: Errors, errorCode: ErrorCode): InvalidParameterException(errorCode, errors)