package com.mejoresiagratis.rellenadorpdv.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mejoresiagratis.rellenadorpdv.data.local.db.entity.ContractHistoryEntity
import com.mejoresiagratis.rellenadorpdv.data.local.db.entity.ContractTemplateEntity
import com.mejoresiagratis.rellenadorpdv.data.local.db.entity.SavedSignatureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContractHistoryDao {
    @Query("SELECT * FROM contract_history ORDER BY savedAtEpochMillis DESC")
    fun observeAll(): Flow<List<ContractHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: ContractHistoryEntity)

    @Query("DELETE FROM contract_history WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM contract_history")
    suspend fun deleteAll()
}

@Dao
interface SavedSignatureDao {
    // Máx. 12 firmas guardadas, igual que la web (auditoría §04.8) — se
    // recorta en el repositorio tras el upsert, no aquí, para mantener el
    // DAO como acceso a datos puro sin política de negocio.
    @Query("SELECT * FROM saved_signatures ORDER BY savedAtEpochMillis DESC")
    fun observeAll(): Flow<List<SavedSignatureEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: SavedSignatureEntity)

    @Query("DELETE FROM saved_signatures WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM saved_signatures")
    suspend fun deleteAll()
}

@Dao
interface ContractTemplateDao {
    @Query("SELECT * FROM contract_templates")
    fun observeAll(): Flow<List<ContractTemplateEntity>>

    @Query("SELECT * FROM contract_templates WHERE fingerprint = :fingerprint LIMIT 1")
    suspend fun findByFingerprint(fingerprint: String): ContractTemplateEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: ContractTemplateEntity)

    @Query("DELETE FROM contract_templates")
    suspend fun deleteAll()
}
