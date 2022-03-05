package com.kiosk.api.store.domain.enums

import com.kiosk.api.common.SuccessMessage

enum class StoreMessage(override val msg: String): SuccessMessage {

    SUCCESS_REGISTER("지점 등록에 성공했습니다."),
    SUCCESS_UPDATE("지점 정보 수정에 성공했습니다."),
    SUCCESS_FIND_ONE("지점 단건 조회에 성공했습니다."),
    SUCCESS_FIND_ALL("지점 목록이 잘 조회되었습니다."),
    SUCCESS_DELETE_ONE("지점 삭제에 성공했습니다.");
}