package com.kiosk.exception.category

import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.common.InvalidParameterException
import org.springframework.validation.Errors

class InvalidCategoryParameterException(errors: Errors, errorCode: ErrorCode): InvalidParameterException(errorCode, errors)