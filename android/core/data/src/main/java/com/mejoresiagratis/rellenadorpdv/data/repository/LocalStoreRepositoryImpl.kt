package com.mejoresiagratis.rellenadorpdv.data.repository

import com.mejoresiagratis.rellenadorpdv.data.local.db.dao.ContractHistoryDao
import com.mejoresiagratis.rellenadorpdv.data.local.db.dao.ContractTemplateDao
import com.mejoresiagratis.rellenadorpdv.data.local.db.dao.SavedSignatureDao
import com.mejoresiagratis.rellenadorpdv.data.local.db.entity.ContractHistoryEntity
import com.mejoresiagratis.rellenadorpdv.data.local.db.entity.ContractTemplateEntity
import com.mejoresiagratis.rellenadorpdv.data.local.db.entity.SavedSignatureEntity
import com.mejoresiagratis.rellenadorpdv.domain.model.ContractHistoryEntry
import com.mejoresiagratis.rellenadorpdv.domain.model.ContractTemplate
import com.mejoresiagratis.rellenadorpdv.domain.model.SavedSignature
import com.mejoresiagratis.rellenadorpdv.domain.repository.LocalStoreRepository
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

/** Igual que la web recorta a 12 firmas guardadas (auditoría §04.8). */
private const val MAX_SAVED_SIGNATURES = 12

class LocalStoreRepositoryImpl @Inject constructor(
    private val historyDao: ContractHistoryDao,
    private val signatureDao: SavedSignatureDao,
    private val templateDao: ContractTemplateDao,
) : LocalStoreRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override fun observeHistory() = historyDao.observeAll().map { list ->
        list.map { entity ->
            ContractHistoryEntry(
                id = entity.id,
                label = entity.label,
                fingerprint = entity.fingerprint,
                fieldValues = json.decodeFromString(entity.fieldValuesJson),
                savedAtEpochMillis = entity.savedAtEpochMillis,
            )
        }
    }

    override suspend fun saveToHistory(entry: ContractHistoryEntry) {
        historyDao.upsert(
            ContractHistoryEntity(
                id = entry.id,
                label = entry.label,
                fingerprint = entry.fingerprint,
                fieldValuesJson = json.encodeToString(entry.fieldValues),
                savedAtEpochMillis = entry.savedAtEpochMillis,
            ),
        )
    }

    override suspend fun deleteFromHistory(id: String) = historyDao.deleteById(id)

    override fun observeSignatures() = signatureDao.observeAll().map { list ->
        list.map { SavedSignature(it.id, it.imagePath, it.aspectRatio, it.savedAtEpochMillis) }
    }

    override suspend fun saveSignature(signature: SavedSignature) {
        signatureDao.upsert(
            SavedSignatureEntity(signature.id, signature.imagePath, signature.aspectRatio, signature.savedAtEpochMillis),
        )
        // TODO Fase 4: recortar a MAX_SAVED_SIGNATURES borrando las más antiguas,
        // igual que hace la web al guardar una firma nueva.
    }

    override suspend fun deleteSignature(id: String) = signatureDao.deleteById(id)

    override fun observeTemplates() = templateDao.observeAll().map { list ->
        list.map { ContractTemplate(it.id, it.fingerprint, json.decodeFromString(it.fieldMappingJson), it.label) }
    }

    override suspend fun saveTemplate(template: ContractTemplate) {
        templateDao.upsert(
            ContractTemplateEntity(
                id = template.id,
                fingerprint = template.fingerprint,
                fieldMappingJson = json.encodeToString(template.fieldMapping),
                label = template.label,
            ),
        )
    }

    override suspend fun purgeAllLocalData() {
        // Equivalente a purgeLocalData() — borra los 3 almacenes de una vez (auditoría §04.9).
        historyDao.deleteAll()
        signatureDao.deleteAll()
        templateDao.deleteAll()
    }
}
