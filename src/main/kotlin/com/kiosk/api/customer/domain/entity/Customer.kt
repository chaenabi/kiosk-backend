package com.kiosk.api.customer.domain.entity

import com.kiosk.api.customer.domain.enums.CustomerGrade
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@DynamicUpdate
class Customer(
    @Id @GeneratedValue @Column(name = "customer_id")
    var id: Long? = null,
    var contactNumber: String?,
    var name: String? = contactNumber?.let { it.substring(it.length - 4) },

    @Convert(converter = YNToBooleanCOnverter::class)
    @Column(length = 1)
    var isActive: Boolean = true,

    @Enumerated(value = EnumType.STRING)
    var role: CustomerGrade = CustomerGrade.NORMAL
) {
    @CreationTimestamp
    lateinit var registerDate: LocalDateTime

    companion object YNToBooleanCOnverter : AttributeConverter<Boolean, String> {
        override fun convertToDatabaseColumn(attribute: Boolean): String {
            return if (attribute) "Y" else "N"
        }

        override fun convertToEntityAttribute(dbData: String): Boolean {
            return dbData == "Y" || dbData == "y"
        }
    }

    override fun toString(): String {
        return "Customer(id=$id, contactNumber=$contactNumber, name=$name, isActive=$isActive, role=$role, registerDate=$registerDate)"
    }


}
