package com.kiosk.api.constant.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Constant(
    @Id @GeneratedValue @Column(name = "constant_id")
    var id: Long? = null,

    var maxOrderTime: Int
)