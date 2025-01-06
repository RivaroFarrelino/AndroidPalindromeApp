package com.example.suitmediamobiledevelopertestquestion1

import com.example.suitmediamobiledevelopertestquestion1.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<ApiResponse>
}