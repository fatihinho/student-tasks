package com.fcinar.studenttasks.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Student")
data class Student(
    @Id
    @Column(name = "Id")
    val id: UUID,

    @Column(name = "Name", length = 50, nullable = false)
    val name: String,

    @Column(name = "Surname", length = 50, nullable = false)
    val surname: String,

    @Column(name = "PhoneNumber", length = 30)
    val phoneNumber: String,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "CityId", nullable = false)
    val city: City,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "DistrictId", nullable = false)
    val district: District,

    @Column(name = "Description", length = 100, nullable = false)
    val description: String,
) {
    constructor(
        name: String,
        surname: String,
        phoneNumber: String,
        city: City,
        district: District,
        description: String
    ) : this(
        id = UUID.randomUUID(),
        name = name,
        surname = surname,
        phoneNumber = phoneNumber,
        city = city,
        district = district,
        description = description
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (id != other.id) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (phoneNumber != other.phoneNumber) return false
        if (city != other.city) return false
        if (district != other.district) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + district.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }

    override fun toString(): String {
        return "Student(id=$id, name='$name', surname='$surname', phoneNumber='$phoneNumber', " +
                "city=$city, district=$district, description='$description')"
    }
}
