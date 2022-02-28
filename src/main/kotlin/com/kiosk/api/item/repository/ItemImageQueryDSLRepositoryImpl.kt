package com.kiosk.api.item.repository

import com.kiosk.api.item.domain.entity.ItemImage
import com.kiosk.api.item.domain.entity.QItemImage
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class ItemImageQueryDSLRepositoryImpl : ItemImageQueryDSLRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    private lateinit var queryFactory: JPAQueryFactory

    override fun findByItemId(id: Long?): MutableIterable<ItemImage> {
        queryFactory = JPAQueryFactory(entityManager)
        val qItemImage: QItemImage = QItemImage.itemImage
        return queryFactory.selectFrom(qItemImage)
            .where(qItemImage.item.id.eq(id))
            .fetch()
    }
}