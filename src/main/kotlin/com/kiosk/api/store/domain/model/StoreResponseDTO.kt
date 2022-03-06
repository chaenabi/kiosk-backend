package com.kiosk.api.store.domain.model

import com.kiosk.api.store.domain.entity.Store
import com.kiosk.api.store.domain.enums.StoreStatus

class StoreResponseDTO {


    data class FindOne(val store: Store) {
        var id: Long? = store.id
        var owner: String? = store.owner
        var city: String? = store.city
        var street: String? = store.street
        var zipCode: String? = store.zipCode
        var status: StoreStatus? = store.status
        var name: String? = store.name
    }

    data class Register(val registeredStore: Store) {
        val id = registeredStore.id
        val owner = registeredStore.owner
        val city = registeredStore.city
        val street = registeredStore.street
        val zipCode = registeredStore.zipCode
        val status = registeredStore.status
        val name = registeredStore.name
    }

    data class Update(val updatedStore: Store) {
        val id = updatedStore.id
        val owner = updatedStore.owner
        val city = updatedStore.city
        val street = updatedStore.street
        val zipCode = updatedStore.zipCode
        val status = updatedStore.status
        val name = updatedStore.name
    }

    data class FindAll(val foundAllStores: List<Store>) {
        val stores = mapping(foundAllStores)

        private fun mapping(foundAllStores: List<Store>): List<FindOne> {
            val list: MutableList<FindOne> = arrayListOf()
            for (foundStore in foundAllStores) {
                list.add(FindOne(foundStore))
            }
            return list
        }
    }

    data class FindRevenue(
        val foundRevenue: List<Store>
    ) {
        val totalPrice: Int
        val name: String
    }
}