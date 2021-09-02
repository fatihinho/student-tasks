package com.fcinar.studenttasks.dto

import com.fcinar.studenttasks.model.City

data class DistrictDto(
    val id: Int?,
    val name: String,
    val city: City
)
