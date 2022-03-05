package com.kiosk.api.store.domain.model

import com.kiosk.api.store.domain.entity.Store
import com.kiosk.api.store.domain.enums.StoreStatus

class StoreResponseDTO(store: Store) {

    var id: Long? = null
    var owner: String? = null
    var city: String? = null
    var street: String? = null
    var zipCode: String? = null
    var status: StoreStatus? = null

    init {
        this.id = store.id
        this.owner = store.owner
        this.city = store.city
        this.street = store.street
        this.zipCode = store.zipCode
        this.status = store.status
    }

    data class Register(val registeredStore: Store) {
        val id = registeredStore.id
        val owner = registeredStore.owner
        val city = registeredStore.city
        val street = registeredStore.street
        val zipCode = registeredStore.zipCode
        val status = registeredStore.status
    }

    data class Update(val updatedStore: Store) {
        val id = updatedStore.id
        val owner = updatedStore.owner
        val city = updatedStore.city
        val street = updatedStore.street
        val zipCode = updatedStore.zipCode
        val status = updatedStore.status
    }

    data class FindAll(val foundAllStores: List<Store>) {
        val stores = mapping(foundAllStores)

        private fun mapping(foundAllStores: List<Store>): List<StoreResponseDTO> {
            val list: MutableList<StoreResponseDTO> = arrayListOf()
            for (foundStore in foundAllStores) {
                list.add(StoreResponseDTO(foundStore))
            }
            return list
        }
    }
}