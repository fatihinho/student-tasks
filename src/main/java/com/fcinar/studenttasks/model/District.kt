package com.fcinar.studenttasks.model

import javax.persistence.*

@Entity
@Table(name = "District")
data class District(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    val id: Int?,

    @Column(name = "Name", length = 30, nullable = false)
    val name: String,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "CityId", nullable = false)
    val city: City
) {
    constructor(
        name: String,
        city: City
    ) : this(
        id = null,
        name = name,
        city = city
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as District

        if (id != other.id) return false
        if (name != other.name) return false
        if (city != other.city) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + city.hashCode()
        return result
    }

    override fun toString(): String {
        return "District(id=$id, name='$name', city=$city)"
    }
}
