package com.kiosk.api.admin.domain.enums

import com.kiosk.api.common.SuccessMessage

enum class AdminMessage(override val msg: String): SuccessMessage {

    SUCCESS_SIGN_IN("로그인에 성공했습니다."),
}