package com.fcinar.studenttasks.dto

import java.util.*

data class StudentImageDto(
    val id: UUID,
    val imageName: String?,
    val type: String?,
    val byte: ByteArray?,
    val studentId: UUID
)
