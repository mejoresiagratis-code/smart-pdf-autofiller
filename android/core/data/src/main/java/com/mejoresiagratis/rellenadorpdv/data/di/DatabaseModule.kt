package com.mejoresiagratis.rellenadorpdv.data.di

import android.content.Context
import androidx.room.Room
import com.mejoresiagratis.rellenadorpdv.data.local.db.AppDatabase
import com.mejoresiagratis.rellenadorpdv.data.local.db.dao.ContractHistoryDao
import com.mejoresiagratis.rellenadorpdv.data.local.db.dao.ContractTemplateDao
import com.mejoresiagratis.rellenadorpdv.data.local.db.dao.SavedSignatureDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            // Sin fallbackToDestructiveMigration: el historial son datos reales de
            // clientes del PdV, no de prueba — cualquier cambio de esquema futuro
            // necesita una Migration explícita, nunca borrado silencioso.
            .build()

    @Provides
    fun provideContractHistoryDao(db: AppDatabase): ContractHistoryDao = db.contractHistoryDao()

    @Provides
    fun provideSavedSignatureDao(db: AppDatabase): SavedSignatureDao = db.savedSignatureDao()

    @Provides
    fun provideContractTemplateDao(db: AppDatabase): ContractTemplateDao = db.contractTemplateDao()
}
