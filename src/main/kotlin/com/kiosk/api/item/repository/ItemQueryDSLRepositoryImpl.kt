package com.kiosk.api.item.repository

import com.kiosk.api.item.domain.entity.Item
import com.kiosk.api.item.domain.entity.QItem
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class ItemQueryDSLRepositoryImpl : ItemQueryDSLRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    private lateinit var queryFactory: JPAQueryFactory

    override fun findItemAsPage(pageable: Pageable): PageImpl<Item> {
        queryFactory = JPAQueryFactory(entityManager)
        val qItem: QItem = QItem.item
        val queryResult = queryFactory.selectFrom(qItem)
                                      .offset(pageable.offset)
                                      .limit(pageable.pageSize.toLong())
                                      .fetch()

        return PageImpl(queryResult, pageable, queryResult.size.toLong())
    }
}