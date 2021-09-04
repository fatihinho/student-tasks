package com.fcinar.studenttasks.dto

import com.fcinar.studenttasks.model.Student
import java.util.*

data class StudentImageDto(
    val id: UUID,
    val imageName: String,
    val type: String,
    val byte: ByteArray,
    val student: Student
)
