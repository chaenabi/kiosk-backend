package com.kiosk.api.store.domain.enums

enum class StoreStatus(var status: String) {
    OPEN("개점"), CLOSE("폐점"), AWAIT("대기")
}