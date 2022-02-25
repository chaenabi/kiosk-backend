package com.kiosk

import com.kiosk.exception.controllerAdvice.GeneralParameterErrorCode
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KioskApplicationTests {

    @Test
    fun contextLoads() {
        println(GeneralParameterErrorCode.INVALID_PARAMETER.findMatchBizCode("a"))
    }

}
