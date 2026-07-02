package com.mejoresiagratis.rellenadorpdv.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Sustituyen a los 3 almacenes de localStorage (auditoría §04.8):
 *   rellenadorHistorial_v1 -> ContractHistoryEntity
 *   rellenadorFirmas_v1    -> SavedSignatureEntity
 *   rellenadorPlantillas_v1 -> ContractTemplateEntity
 *
 * Los mapas (fieldValues, fieldMapping) se guardan como JSON en una columna
 * TEXT vía TypeConverters — mismo patrón que ya usa la web con
 * JSON.stringify/parse, sin reinventar el modelo de datos en la migración.
 */

@Entity(tableName = "contract_history")
data class ContractHistoryEntity(
    @PrimaryKey val id: String,
    val label: String,
    val fingerprint: String,
    val fieldValuesJson: String,
    val savedAtEpochMillis: Long,
)

@Entity(tableName = "saved_signatures")
data class SavedSignatureEntity(
    @PrimaryKey val id: String,
    val imagePath: String, // archivo en almacenamiento interno, NUNCA blob en la tabla (auditoría §04.8)
    val aspectRatio: Float,
    val savedAtEpochMillis: Long,
)

@Entity(tableName = "contract_templates")
data class ContractTemplateEntity(
    @PrimaryKey val id: String,
    val fingerprint: String,
    val fieldMappingJson: String,
    val label: String,
)
