package com.kiosk.api.constant.domain.entity

import javax.persistence.*

@Entity
class Constant(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "constant_id")
    var id: Long? = null,

    var maxOrderTime: Int
)