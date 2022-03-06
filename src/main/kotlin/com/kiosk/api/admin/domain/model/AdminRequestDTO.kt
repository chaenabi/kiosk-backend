package com.kiosk.api.admin.domain.model

import com.kiosk.api.admin.domain.entity.Admin
import com.kiosk.api.store.domain.entity.Store
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class AdminRequestDTO {

    class SignIn(
       @field:NotBlank(message = "관리자 아이디가 반드시 전달되어야 합니다.")
       val name: String,
       @field:NotBlank(message = "관리자 비밀번호가 전달되지 않았습니다.")
       val password: String
    ) {
        fun toEntity(): Admin {
            return Admin(
                name = name,
                password = password
            )
        }
    }

    class SignUp(
        @field:NotBlank(message = "관리자 아이디가 반드시 전달되어야 합니다.")
        val name: String,
        @field:NotBlank(message = "관리자 비밀번호가 전달되지 않았습니다.")
        val password: String,
        val store: Store
    ) {
        fun toEntity(): Admin {
            return Admin(
                name = name,
                password = password,
                store = store
            )
        }
    }

    class Update(
        @field:Positive(message = "관리자 번호가 반드시 전달되어야 합니다.")
        val id: Long,
        val name: String?,
        val password: String?,
        val store: Store?
    ) {
        fun toEntity(): Admin {
            return Admin(
                name = name,
                password = password,
                store = store
            )
        }
    }
}