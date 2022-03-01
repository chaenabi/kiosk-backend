package com.kiosk.api.category.repository

import com.kiosk.api.category.domain.entity.CategoryItem
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class CategoryQueryDSLRepositoryImpl : CategoryQueryDSLRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    private lateinit var queryFactory: JPAQueryFactory

    override fun saveCategoryItem(categoryItem: CategoryItem) {
        entityManager.createNativeQuery(
            "insert into category_item (category_id, item_id) " +
                    "select c.category_id, i.item_id " +
                    "from category c, item i " +
                    "where c.category_id = ${categoryItem.category.id} " +
                    "and i.item_id = ${categoryItem.item.id}"
        )
        .executeUpdate()
    }

    override fun drawOffCategoryItem(categoryItem: CategoryItem) {
        entityManager.createNativeQuery(
            "delete from category_item c " +
                    "where c.category_id = ${categoryItem.category.id} " +
                    "and c.item_id = ${categoryItem.item.id}"
        )
        .executeUpdate()
    }


}