package com.mejoresiagratis.rellenadorpdv.domain.repository

import com.mejoresiagratis.rellenadorpdv.domain.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Fuente de verdad única del documento en edición. Implementación real en
 * core:data (PdfRepositoryImpl, envolviendo PdfBox-Android) — Singleton
 * vía Hilt para no duplicar el documento de 54 páginas en memoria.
 * Implementación pendiente de Fase 2/3; esta interfaz es el contrato.
 */
interface PdfRepository {
    val fields: Flow<List<PdfField>>
    val signatureGaps: Flow<List<SignatureGap>>
    val totalPages: Flow<Int>

    suspend fun loadDocument(bytes: ByteArray, sourceName: String): Result<Unit>
    suspend fun updateFieldValue(fieldId: String, value: String)
    suspend fun buildFilledBytes(): Result<ByteArray>
    suspend fun stampSignature(pageIndex: Int, signatureImage: ByteArray, x: Float, y: Float, width: Float, height: Float): Result<Unit>
    fun clear()
}

/**
 * Reenvía peticiones al mismo ai-proxy.php que ya usa la web — sin
 * cambios en el backend, solo cambia el cliente HTTP (auditoría §07).
 */
interface AiProxyRepository {
    suspend fun getAvailableProviders(): Result<Map<String, Boolean>>
    suspend fun extract(providerId: String, prompt: String, documents: List<DocumentPayload>): Result<String>
    suspend fun locateSignature(providerId: String, imageBase64: String): Result<String>
}

data class DocumentPayload(val mimeType: String, val base64: String, val text: String? = null)

/** Historial, firmas y plantillas — Room + almacenamiento interno, sustituye a localStorage. */
interface LocalStoreRepository {
    fun observeHistory(): Flow<List<ContractHistoryEntry>>
    suspend fun saveToHistory(entry: ContractHistoryEntry)
    suspend fun deleteFromHistory(id: String)

    fun observeSignatures(): Flow<List<SavedSignature>>
    suspend fun saveSignature(signature: SavedSignature)
    suspend fun deleteSignature(id: String)

    fun observeTemplates(): Flow<List<ContractTemplate>>
    suspend fun saveTemplate(template: ContractTemplate)

    suspend fun purgeAllLocalData()
}

/** Preferencias ligeras — DataStore, sustituye a las claves sueltas de localStorage. */
interface UserPreferencesRepository {
    val preferences: Flow<UserPreferences>
    suspend fun setDarkTheme(enabled: Boolean?)
    suspend fun setEuOnlyMode(enabled: Boolean)
    suspend fun setConsentRemembered(remembered: Boolean)
}
