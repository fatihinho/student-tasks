package com.fcinar.studenttasks.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "StudentImage")
data class StudentImage(
    @Id
    @Column(name = "Id")
    val id: UUID,

    @Column(name = "ImageName", nullable = false)
    val imageName: String,

    @Column(name = "Type", nullable = false)
    val type: String,

    @Column(name = "Byte", length = 10000000, nullable = false)
    val byte: ByteArray,

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    val student: Student
) {
    constructor(
        imageName: String,
        type: String,
        byte: ByteArray,
        student: Student
    ) : this(
        id = UUID.randomUUID(),
        imageName = imageName,
        type = type,
        byte = byte,
        student = student
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StudentImage

        if (id != other.id) return false
        if (imageName != other.imageName) return false
        if (type != other.type) return false
        if (!byte.contentEquals(other.byte)) return false
        if (student != other.student) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageName.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + byte.contentHashCode()
        result = 31 * result + student.hashCode()
        return result
    }

    override fun toString(): String {
        return "StudentImage(id=$id, imageName='$imageName', " +
                "type='$type', byte=${byte.contentToString()}, student=$student)"
    }
}
