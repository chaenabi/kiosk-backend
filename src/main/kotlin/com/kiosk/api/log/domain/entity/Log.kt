package com.kiosk.api.log.domain.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Log(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "log_id")
    var id: Long? = null,

    var content: String? = null,

    @CreationTimestamp
    var createDate: LocalDateTime = LocalDateTime.now()
)