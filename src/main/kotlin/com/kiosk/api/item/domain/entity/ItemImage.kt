package com.kiosk.api.item.domain.entity

import javax.persistence.*

@Entity
class ItemImage(

    @Id @GeneratedValue @Column(name = "item_image_id")
    var id: Long? = null,
    var name: String? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "item_id")
    var item: Item
)