package com.kiosk.api.admin.repository

import com.kiosk.api.admin.domain.entity.QAdmin
import com.kiosk.api.admin.domain.model.AdminResponseDTO
import com.kiosk.api.store.domain.entity.QStore
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class AdminRepositorySupportImpl : AdminRepositorySupport {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    private lateinit var queryFactory: JPAQueryFactory

    override fun findByName(name: String): AdminResponseDTO.SignInMapper? {
        queryFactory = JPAQueryFactory(entityManager)
        val qAdmin: QAdmin = QAdmin.admin
        val qStore: QStore = qAdmin.store

        return queryFactory.select(
            Projections.constructor(
                AdminResponseDTO.SignInMapper::class.java,
                qAdmin.id,
                qAdmin.name,
                qAdmin.password,
                qStore.id,
                qStore.name
            )
        )
            .from(qAdmin).innerJoin(qStore)
            .where(qAdmin.name.eq(name))
            .fetchOne()
    }
}