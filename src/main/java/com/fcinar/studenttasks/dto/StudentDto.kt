package com.fcinar.studenttasks.dto

import com.fcinar.studenttasks.model.City
import com.fcinar.studenttasks.model.District
import java.util.*

data class StudentDto(
    val id: UUID,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val city: City,
    val district: District,
    val description: String?,
    val imageByte: ByteArray?
)
