package com.example.weatherapp.model.interceptor

import com.example.weatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("X-RapidAPI-Key", BuildConfig.API_KEY)
            .header("X-RapidAPI-Host", BuildConfig.X_RapidAPI_Host)
            .build()
        return chain.proceed(request)
    }
}