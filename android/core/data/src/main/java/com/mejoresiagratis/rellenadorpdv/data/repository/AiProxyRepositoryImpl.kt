package com.mejoresiagratis.rellenadorpdv.data.repository

import com.mejoresiagratis.rellenadorpdv.data.remote.AiProxyApi
import com.mejoresiagratis.rellenadorpdv.data.remote.dto.ProxyDocumentDto
import com.mejoresiagratis.rellenadorpdv.data.remote.dto.ProxyExtractRequest
import com.mejoresiagratis.rellenadorpdv.domain.repository.AiProxyRepository
import com.mejoresiagratis.rellenadorpdv.domain.repository.DocumentPayload
import javax.inject.Inject

/**
 * Traduce 1:1 a probeProxy()/callViaProxy() de rellenador-pro.html — mismo
 * endpoint, mismo shape de petición/respuesta. Cero lógica de negocio aquí
 * (eso es ExtractDataFromDocumentUseCase, Fase 2): esta clase solo sabe
 * hablar HTTP con ai-proxy.php.
 */
class AiProxyRepositoryImpl @Inject constructor(
    private val api: AiProxyApi,
) : AiProxyRepository {

    override suspend fun getAvailableProviders(): Result<Map<String, Boolean>> = runCatching {
        val response = api.getStatus()
        check(response.ok) { response.error ?: "ai-proxy.php devolvió ok=false" }
        response.providers
    }

    override suspend fun extract(
        providerId: String, prompt: String, documents: List<DocumentPayload>,
    ): Result<String> = runCatching {
        val response = api.extract(
            ProxyExtractRequest(
                provider = providerId,
                prompt = prompt,
                docs = documents.map { ProxyDocumentDto(mime = it.mimeType, b64 = it.base64) },
                task = "extract",
                maxTokens = 2048, // mismo valor que usa hoy runExtractAI()
            ),
        )
        check(response.ok) { response.error ?: "Proveedor $providerId devolvió ok=false" }
        response.text.orEmpty()
    }

    override suspend fun locateSignature(providerId: String, imageBase64: String): Result<String> = runCatching {
        val response = api.extract(
            ProxyExtractRequest(
                provider = providerId,
                prompt = "", // el prompt de localización vive en el UseCase, no aquí (Fase 4)
                docs = listOf(ProxyDocumentDto(mime = "image/jpeg", b64 = imageBase64)),
                task = "locate_signature",
                maxTokens = 300, // mismo valor que usa hoy aiLocateSignature()
            ),
        )
        check(response.ok) { response.error ?: "Proveedor $providerId devolvió ok=false" }
        response.text.orEmpty()
    }
}
