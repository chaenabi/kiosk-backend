package com.kiosk.api.order.domain.enums

import com.kiosk.api.common.SuccessMessage

enum class OrderMessage(override val msg: String): SuccessMessage {

    SUCCESS_ADD("주문이 성공적으로 접수되었습니다."),
    SUCCESS_UPDATE("주문 정보 수정에 성공했습니다."),
    SUCCESS_FIND_ONE("주문 단건 조회에 성공했습니다."),
    SUCCESS_FIND_ALL("주문 목록이 잘 조회되었습니다."),
    SUCCESS_CANCEL("주문 취소에 성공했습니다.");
}