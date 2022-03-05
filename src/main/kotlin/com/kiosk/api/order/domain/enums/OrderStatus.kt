package com.kiosk.api.order.domain.enums

enum class OrderStatus(var status: String) {
    COMPLETE("완료"), WAIT("대기"), SUSPEND("중단"), FAIL("실패"), CANCEL("취소")
}