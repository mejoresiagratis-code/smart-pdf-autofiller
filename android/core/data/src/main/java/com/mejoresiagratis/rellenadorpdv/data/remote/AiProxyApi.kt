package com.mejoresiagratis.rellenadorpdv.data.remote

import com.mejoresiagratis.rellenadorpdv.data.remote.dto.ProxyExtractRequest
import com.mejoresiagratis.rellenadorpdv.data.remote.dto.ProxyExtractResponse
import com.mejoresiagratis.rellenadorpdv.data.remote.dto.ProxyStatusResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Mismo endpoint que ya usa rellenador-pro.html vía fetch() (probeProxy()/
 * callViaProxy()) — la URL base se inyecta desde BuildConfig.AI_PROXY_BASE_URL
 * (ver NetworkModule), configurable por local.properties sin tocar código.
 */
interface AiProxyApi {

    @GET("ai-proxy.php")
    suspend fun getStatus(): ProxyStatusResponse

    @POST("ai-proxy.php")
    suspend fun extract(@Body request: ProxyExtractRequest): ProxyExtractResponse
}
