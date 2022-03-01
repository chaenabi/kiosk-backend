package com.kiosk.api.category.domain.enums

import com.kiosk.api.common.SuccessMessage

enum class CategoryMessage(override val msg: String): SuccessMessage {

    SUCCESS_ADD("새로운 카테고리를 추가하는 것에 성공했습니다."),
    SUCCESS_ADD_ITEMS("아이템을 카테고리에 등록하는 것에 성공했습니다."),
    SUCCESS_DRAW_OFF_ITEMS("카테고리에서 아이템을 제거하는 것에 성공했습니다."),
    SUCCESS_UPDATE("카테고리 내용 수정에 성공했습니다."),
    SUCCESS_FIND_ONE("카테고리 조회에 성공했습니다."),
    SUCCESS_DELETE_ONE("카테고리 단건 삭제에 성공했습니다.");
}