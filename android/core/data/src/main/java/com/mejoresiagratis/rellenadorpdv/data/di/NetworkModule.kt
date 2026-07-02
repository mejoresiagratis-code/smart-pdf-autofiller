package com.mejoresiagratis.rellenadorpdv.data.di

import com.mejoresiagratis.rellenadorpdv.data.remote.AiProxyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Único lugar donde se construye el cliente HTTP hacia ai-proxy.php.
 * La URL base viene de BuildConfig (ver app/build.gradle.kts), que a su vez
 * la lee de local.properties — nunca hardcodeada, tal y como exige el
 * protocolo del proyecto (equivalente Android al .env).
 *
 * Timeouts generosos a propósito: los motores con visión nativa de PDF
 * (Claude, Gemini) tardan más que los de solo texto — la propia auditoría
 * señala a Gemini como el más lento del lote (§11 de la guía original).
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(networkLoggingEnabled: NetworkLoggingEnabled): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .apply {
                if (networkLoggingEnabled.value) {
                    addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
                }
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, json: Json, baseUrl: AiProxyBaseUrl): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl.value)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun provideAiProxyApi(retrofit: Retrofit): AiProxyApi = retrofit.create(AiProxyApi::class.java)
}

/** Wrappers con nombre para no inyectar Strings/Booleans "a pelo" por todo el grafo de Hilt. */
@JvmInline value class AiProxyBaseUrl(val value: String)
@JvmInline value class NetworkLoggingEnabled(val value: Boolean)
