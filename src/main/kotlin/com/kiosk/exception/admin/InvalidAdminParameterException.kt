package com.kiosk.exception.admin

import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.common.InvalidParameterException
import org.springframework.validation.Errors

class InvalidAdminParameterException(errors: Errors, errorCode: ErrorCode): InvalidParameterException(errorCode, errors)