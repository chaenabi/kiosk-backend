package com.kiosk.api.item.domain.entity

import javax.persistence.*

@Entity
class ItemImage(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "item_image_id")
    var id: Long? = null,
    var name: String? = null,
    var path: String? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "item_id")
    var item: Item
)