package com.mejoresiagratis.rellenadorpdv.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Espejo EXACTO del JSON que ya devuelve ai-proxy.php en producción — no se
 * toca el backend, así que estos DTO son el contrato, no una propuesta.
 * Ver auditoría §04.5 y ai-proxy.php líneas ~1-40 para el shape real.
 */

@Serializable
data class ProxyStatusResponse(
    val ok: Boolean,
    val providers: Map<String, Boolean> = emptyMap(), // claude/gemini/groq/grok/mistral/scaleway/ovh/nebius/eurouter
    val error: String? = null,
)

@Serializable
data class ProxyDocumentDto(
    val mime: String,
    val b64: String,
)

@Serializable
data class ProxyExtractRequest(
    val provider: String,
    val prompt: String,
    val docs: List<ProxyDocumentDto>,
    val task: String, // "extract" | "locate_signature"
    @SerialName("max_tokens") val maxTokens: Int,
)

@Serializable
data class ProxyExtractResponse(
    val ok: Boolean,
    val text: String? = null, // respuesta cruda del modelo — el propio front hace parseAIJson() sobre esto
    val error: String? = null,
)
