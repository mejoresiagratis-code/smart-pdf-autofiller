package com.mejoresiagratis.rellenadorpdv.data.di

import com.mejoresiagratis.rellenadorpdv.data.repository.AiProxyRepositoryImpl
import com.mejoresiagratis.rellenadorpdv.data.repository.LocalStoreRepositoryImpl
import com.mejoresiagratis.rellenadorpdv.data.repository.PdfRepositoryImpl
import com.mejoresiagratis.rellenadorpdv.data.repository.UserPreferencesRepositoryImpl
import com.mejoresiagratis.rellenadorpdv.domain.repository.AiProxyRepository
import com.mejoresiagratis.rellenadorpdv.domain.repository.LocalStoreRepository
import com.mejoresiagratis.rellenadorpdv.domain.repository.PdfRepository
import com.mejoresiagratis.rellenadorpdv.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Binds en vez de @Provides: son interfaz -> implementación 1:1, sin
 * lógica de construcción — @Binds es más barato en tiempo de compilación
 * y es el patrón recomendado por Hilt para este caso exacto.
 *
 * Esta es la pieza que hace que ExtractDataFromDocumentUseCase (dominio)
 * pueda pedir un AiProxyRepository sin saber nunca que existe Retrofit.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPdfRepository(impl: PdfRepositoryImpl): PdfRepository

    @Binds
    abstract fun bindAiProxyRepository(impl: AiProxyRepositoryImpl): AiProxyRepository

    @Binds
    abstract fun bindLocalStoreRepository(impl: LocalStoreRepositoryImpl): LocalStoreRepository

    @Binds
    abstract fun bindUserPreferencesRepository(impl: UserPreferencesRepositoryImpl): UserPreferencesRepository
}
