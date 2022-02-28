package com.kiosk

import com.kiosk.exception.customer.CustomerControllerAdvice
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
class KioskApplication

fun main(args: Array<String>) {
    runApplication<KioskApplication>(*args)
}
