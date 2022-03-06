package com.kiosk.api.store.repository

import com.kiosk.api.store.domain.entity.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StoreRepository : JpaRepository<Store, Long> {
    fun findByName(name: String): Optional<Store>
}