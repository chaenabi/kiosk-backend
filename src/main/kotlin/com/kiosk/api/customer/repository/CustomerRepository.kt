package com.kiosk.api.customer.repository

import com.kiosk.api.customer.domain.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {
    fun findByName(name: String): Optional<Customer>
}