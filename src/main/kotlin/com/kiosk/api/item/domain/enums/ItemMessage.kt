package com.kiosk.api.item.domain.enums

import com.kiosk.api.common.SuccessMessage

enum class ItemMessage(override val msg: String): SuccessMessage {

    SUCCESS_REGISTER("상품 등록에 성공했습니다."),
    SUCCESS_UPDATE("상품 정보 수정에 성공했습니다."),
    SUCCESS_FIND_ONE("상품 단건 조회에 성공했습니다."),
    SUCCESS_FIND_AS_PAGE("상품 페이지가 잘 조회되었습니다."),
    SUCCESS_DELETE_ONE("상품 단건 삭제에 성공했습니다.");
}