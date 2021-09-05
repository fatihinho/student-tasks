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
    var imageName: String,

    @Column(name = "Type", nullable = false)
    var type: String,

    @Column(name = "Byte", length = 10000000, nullable = false)
    var byte: ByteArray,

    @Column(name = "StudentId", nullable = false)
    val studentId: UUID
) {
    constructor(
        imageName: String,
        type: String,
        byte: ByteArray,
        studentId: UUID
    ) : this(
        id = UUID.randomUUID(),
        imageName = imageName,
        type = type,
        byte = byte,
        studentId = studentId
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StudentImage

        if (id != other.id) return false
        if (imageName != other.imageName) return false
        if (type != other.type) return false
        if (!byte.contentEquals(other.byte)) return false
        if (studentId != other.studentId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageName.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + byte.contentHashCode()
        result = 31 * result + studentId.hashCode()
        return result
    }

    override fun toString(): String {
        return "StudentImage(id=$id, imageName='$imageName', type='$type', " +
                "byte=${byte.contentToString()}, studentId=$studentId)"
    }
}
