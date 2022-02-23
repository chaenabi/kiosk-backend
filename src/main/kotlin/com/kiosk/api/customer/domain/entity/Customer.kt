package com.kiosk.api.customer.domain.entity

import com.kiosk.api.customer.domain.enums.Grade
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "customers")
class Customer(
    @Id @GeneratedValue @Column(name = "customer_id")
    var id: Long?,

    @CreationTimestamp
    var registerDate: LocalDateTime,

    @Convert(converter = YNToBooleanCOnverter::class)
    var isActive: Boolean,

    @Enumerated(value = EnumType.STRING)
    var role: Grade
) {
    companion object YNToBooleanCOnverter: AttributeConverter<Boolean, String> {
        override fun convertToDatabaseColumn(attribute: Boolean): String {
            return if (attribute) "Y" else "N"
        }

        override fun convertToEntityAttribute(dbData: String): Boolean {
            return dbData == "Y" || dbData == "y"
        }
    }
}
