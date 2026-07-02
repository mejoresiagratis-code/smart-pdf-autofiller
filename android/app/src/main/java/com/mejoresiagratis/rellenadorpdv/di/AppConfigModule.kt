package com.mejoresiagratis.rellenadorpdv.di

import com.mejoresiagratis.rellenadorpdv.BuildConfig
import com.mejoresiagratis.rellenadorpdv.data.di.AiProxyBaseUrl
import com.mejoresiagratis.rellenadorpdv.data.di.NetworkLoggingEnabled
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * core:data no puede depender de :app (rompería el grafo de módulos —
 * :app es quien depende de core:data, nunca al revés), así que la lectura
 * de BuildConfig.AI_PROXY_BASE_URL vive aquí, en el único módulo que sí
 * conoce BuildConfig, y se expone a NetworkModule (core:data) como un
 * value class simple. Si BASE_CONTRACT_URL necesita lo mismo, añadir aquí.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppConfigModule {

    @Provides
    fun provideAiProxyBaseUrl(): AiProxyBaseUrl = AiProxyBaseUrl(BuildConfig.AI_PROXY_BASE_URL)

    @Provides
    fun provideNetworkLoggingEnabled(): NetworkLoggingEnabled = NetworkLoggingEnabled(BuildConfig.NETWORK_LOGGING)
}
