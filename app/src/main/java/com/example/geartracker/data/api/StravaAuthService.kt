package com.example.geartracker.data.api

import android.content.Context
import com.example.geartracker.data.model.StravaTokenResponse
import retrofit2.http.POST
import retrofit2.http.Query

fun startStravaAuth(context: Context) {
    val intentUri = android.net.Uri.parse("https://www.strava.com/oauth/mobile/authorize")
        .buildUpon()
        .appendQueryParameter("client_id", "187151")
        .appendQueryParameter("redirect_uri", "geartracker://geartracker.karoada.ovh")
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("approval_prompt", "auto")
        .appendQueryParameter("scope", "activity:read_all")
        .build()

    val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, intentUri)

    context.startActivity(intent)
}

interface StravaAuthService {

    @POST("oauth/token")
    suspend fun getAccessToken(
        @Query("client_id") clientId: Int,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String = "authorization_code"
    ): StravaTokenResponse

    @POST("oauth/token")
    suspend fun refreshToken(
        @Query("client_id") clientId: Int,
        @Query("client_secret") clientSecret: String,
        @Query("refresh_token") refreshToken: String,
        @Query("grant_type") grantType: String = "refresh_token"
    ): StravaTokenResponse
}