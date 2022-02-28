package com.kiosk.api.customer.domain.enums

import com.kiosk.api.common.SuccessMessage

enum class CustomerMessage(override val msg: String): SuccessMessage {

    SUCCESS_REGISTER("사용자 등록에 성공했습니다."),
    SUCCESS_UPDATE("사용자 정보 수정에 성공했습니다."),
    SUCCESS_FIND_ONE("사용자 단건 조회에 성공했습니다."),
    SUCCESS_DELETE_ONE("사용자 단건 삭제에 성공했습니다.");
}