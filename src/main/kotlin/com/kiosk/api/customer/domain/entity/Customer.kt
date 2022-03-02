package com.kiosk.api.customer.domain.entity

import com.kiosk.api.customer.domain.enums.CustomerGrade
import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@DynamicUpdate
class Customer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "customer_id")
    var id: Long? = null,
    var contactNumber: String? = null,
    var name: String? = null,

    @Convert(converter = YNToBooleanConverter::class)
    @Column(length = 1)
    var isActive: Boolean? = true,

    @Enumerated(value = EnumType.STRING)
    var role: CustomerGrade? = CustomerGrade.NORMAL,

    @CreationTimestamp
    var registerDate: LocalDateTime = LocalDateTime.now()
) {

    init {
       name = name ?: contactNumber?.let { it.substring(it.length - 4) }
    }

    fun updateCustomer(customer: CustomerRequestDTO.Update): Customer {
        this.name = customer.name ?: this.name
        this.role = customer.role ?: this.role
        this.contactNumber = customer.contactNumber ?: this.contactNumber
        this.isActive = this.isActive ?: this.isActive
        return this
    }

    companion object YNToBooleanConverter : AttributeConverter<Boolean, String> {
        override fun convertToDatabaseColumn(attribute: Boolean): String {
            return if (attribute) "Y" else "N"
        }

        override fun convertToEntityAttribute(dbData: String): Boolean {
            return dbData == "Y" || dbData == "y"
        }
    }

}
