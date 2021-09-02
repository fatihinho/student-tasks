package com.fcinar.studenttasks.model

import javax.persistence.*

@Entity
@Table(name = "City")
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    val id: Int?,

    @Column(name = "Name", length = 30, nullable = false)
    val name: String,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val districts: List<District>
) {
    constructor(
        name: String,
        districts: List<District>
    ) : this(
        id = null,
        name = name,
        districts = districts
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as City

        if (id != other.id) return false
        if (name != other.name) return false
        if (districts != other.districts) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + districts.hashCode()
        return result
    }

    override fun toString(): String {
        return "City(id=$id, name='$name', districts=$districts)"
    }
}
