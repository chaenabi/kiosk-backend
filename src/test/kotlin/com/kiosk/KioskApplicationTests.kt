package com.kiosk

import com.kiosk.exception.common.controllerAdvice.GeneralParameterErrorCode
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KioskApplicationTests {

    @Test
    fun contextLoads() {
        println(
            GeneralParameterErrorCode.INVALID_PARAMETER.
        findMatchBizCode("매개변수가 충분히 전달되지 못했거나 올바르지 않은 매개변수 값이 전달되었습니다."))
        println(
            GeneralParameterErrorCode.INVALID_PARAMETER.
        findMatchBizCode("a"))
    }

}
