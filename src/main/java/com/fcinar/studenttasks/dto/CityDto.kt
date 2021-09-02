package com.fcinar.studenttasks.dto

import com.fcinar.studenttasks.model.District

data class CityDto(
    val id: Int?,
    val name: String,
    val districts: List<District>
)
