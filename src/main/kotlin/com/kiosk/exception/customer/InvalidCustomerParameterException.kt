package com.kiosk.exception.customer

import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.common.InvalidParameterException
import org.springframework.validation.Errors

class InvalidCustomerParameterException(errors: Errors, errorCode: ErrorCode): InvalidParameterException(errorCode, errors)