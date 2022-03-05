package com.kiosk.exception.store

import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.common.InvalidParameterException
import org.springframework.validation.Errors

class InvalidStoreParameterException(errors: Errors, errorCode: ErrorCode): InvalidParameterException(errorCode, errors)