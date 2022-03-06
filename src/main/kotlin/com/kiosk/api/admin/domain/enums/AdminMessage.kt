package com.kiosk.api.admin.domain.enums

import com.kiosk.api.common.SuccessMessage

enum class AdminMessage(override val msg: String): SuccessMessage {
    SUCCESS_SIGN_IN("로그인에 성공했습니다."),
    SUCCESS_SIGN_UP("회원가입에 성공했습니다."),
    SUCCESS_UPDATE("관리자 정보 수정에 성공했습니다."),
    SUCCESS_REMOVE("관리자 정보 삭제에 성공했습니다."),
}