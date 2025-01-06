package com.example.suitmediamobiledevelopertestquestion1

import com.example.suitmediamobiledevelopertestquestion1.models.User
data class ApiResponse(
    val data: List<User>,
    val total_pages: Int
)
