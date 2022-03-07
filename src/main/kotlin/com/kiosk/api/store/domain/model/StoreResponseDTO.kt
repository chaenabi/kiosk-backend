package com.kiosk.api.store.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.kiosk.api.store.domain.entity.Store
import com.kiosk.api.store.domain.enums.StoreStatus
import java.time.LocalDateTime

class StoreResponseDTO {

    data class Register(private val registeredStore: Store) {
        val id = registeredStore.id
        val owner = registeredStore.owner
        val city = registeredStore.city
        val street = registeredStore.street
        val zipCode = registeredStore.zipCode
        val status = registeredStore.status
        val name = registeredStore.name
    }

    data class Update(private val updatedStore: Store) {
        val id = updatedStore.id
        val owner = updatedStore.owner
        val city = updatedStore.city
        val street = updatedStore.street
        val zipCode = updatedStore.zipCode
        val status = updatedStore.status
        val name = updatedStore.name
    }

    data class FindOne(private val store: Store) {
        var id: Long? = store.id
        var owner: String? = store.owner
        var city: String? = store.city
        var street: String? = store.street
        var zipCode: String? = store.zipCode
        var status: StoreStatus? = store.status
        var name: String? = store.name
        val howManyAdmins: Int = store.admins.size
    }

    data class FindAll(private val foundAllStores: List<Store>) {
        val stores = mapping(foundAllStores)

        private fun mapping(foundAllStores: List<Store>): List<FindOne> {
            val list: MutableList<FindOne> = arrayListOf()
            for (foundStore in foundAllStores) {
                list.add(FindOne(foundStore))
            }
            return list
        }
    }

    class FindRevenue(
        private val _store: Store,
        private val _soldList: List<Sold>
    ) {
        val storeName: String? = _store.name
        val soldList: List<Sold> = _soldList
        val totalMoney: Int = calculatePeriodTotalMoney()

        private fun calculatePeriodTotalMoney(): Int {
            var sum = 0
            _soldList.forEach {
                sum += it.totalPrice
            }
            return sum
        }

        companion object {
            fun mapping(store: Store): List<Sold> {
                val orders = store.orders
                val soldList: MutableList<Sold> = arrayListOf()

                for (order in orders) {
                    soldList.add(
                        Sold(
                            order.orderDate,
                            order.getTotalPrice(),
                            order.orderItems[0].item!!.name,
                            order.orderItems[0].count
                        )
                    )
                }

                return soldList
            }
        }

        class Sold(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            val orderDate: LocalDateTime,
            val totalPrice: Int,
            val itemName: String,
            val soldItemCount: Int
        )
    }
}