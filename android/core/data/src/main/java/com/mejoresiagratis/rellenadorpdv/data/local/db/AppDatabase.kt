package com.mejoresiagratis.rellenadorpdv.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mejoresiagratis.rellenadorpdv.data.local.db.dao.ContractHistoryDao
import com.mejoresiagratis.rellenadorpdv.data.local.db.dao.ContractTemplateDao
import com.mejoresiagratis.rellenadorpdv.data.local.db.dao.SavedSignatureDao
import com.mejoresiagratis.rellenadorpdv.data.local.db.entity.ContractHistoryEntity
import com.mejoresiagratis.rellenadorpdv.data.local.db.entity.ContractTemplateEntity
import com.mejoresiagratis.rellenadorpdv.data.local.db.entity.SavedSignatureEntity

@Database(
    entities = [ContractHistoryEntity::class, SavedSignatureEntity::class, ContractTemplateEntity::class],
    version = 1,
    exportSchema = true, // los .json de esquema quedan versionados en /schemas — migraciones futuras sin adivinar
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contractHistoryDao(): ContractHistoryDao
    abstract fun savedSignatureDao(): SavedSignatureDao
    abstract fun contractTemplateDao(): ContractTemplateDao

    companion object {
        const val DATABASE_NAME = "rellenador_pdv.db"
    }
}
