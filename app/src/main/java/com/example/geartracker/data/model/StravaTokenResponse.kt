package com.example.geartracker.data.model

import com.google.gson.annotations.SerializedName

data class StravaTokenResponse(
    @SerializedName("token_type")
    val tokenType: String,

    @SerializedName("expires_at")
    val expiresAt: Long,

    @SerializedName("expires_in")
    val expiresIn: Int,

    @SerializedName("refresh_token")
    val refreshToken: String,

    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("athlete")
    val athlete: StravaAthlete? = null
)

data class StravaAthlete(
    val id: Long,
    val username: String?,
    @SerializedName("firstname")
    val firstName: String?,
    @SerializedName("lastname")
    val lastName: String?
)