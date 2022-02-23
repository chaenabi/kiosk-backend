package com.kiosk.api.order.domain.enums

enum class OrderStatus(var status: String) {
    SUCCESS("완료"), WAIT("대기"), SUSPEND("일시중단"), FAIL("실패")
}