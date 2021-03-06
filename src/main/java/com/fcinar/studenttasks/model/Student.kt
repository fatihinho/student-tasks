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
    var name: String,

    @Column(name = "Surname", length = 50, nullable = false)
    var surname: String,

    @Column(name = "PhoneNumber", length = 30)
    var phoneNumber: String,

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CityId", nullable = false)
    var city: City,

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DistrictId", nullable = false)
    var district: District,

    @Column(name = "Description", length = 100)
    var description: String?,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "ImageId")
    var studentImage: StudentImage?
) {
    constructor(
        name: String,
        surname: String,
        phoneNumber: String,
        city: City,
        district: District,
        description: String?,
        studentImage: StudentImage?
    ) : this(
        id = UUID.randomUUID(),
        name = name,
        surname = surname,
        phoneNumber = phoneNumber,
        city = city,
        district = district,
        description = description,
        studentImage = studentImage
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
        if (studentImage != other.studentImage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + district.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (studentImage?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Student(id=$id, name='$name', surname='$surname', phoneNumber='$phoneNumber', " +
                "city=$city, district=$district, description=$description, studentImage=$studentImage)"
    }
}
