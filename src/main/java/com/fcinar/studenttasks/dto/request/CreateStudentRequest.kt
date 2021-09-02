package com.fcinar.studenttasks.dto.request

data class CreateStudentRequest(
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val cityId: Int,
    val districtId: Int,
    val description: String
)
