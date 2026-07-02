# Fase 1 · Infraestructura de Datos y DI — Entrega

Scaffold completo del proyecto Android multi-módulo. **No hay lógica de negocio
real todavía** — eso es la Fase 2, tal y como fija el plan de ruta aprobado
en el informe de auditoría. Lo que sí hay es la infraestructura completa para
que la Fase 2 sea "rellenar cuerpos de función", no "decidir arquitectura".

## Qué se entrega

- Proyecto Gradle multi-módulo completo: `:app`, `:core:domain`, `:core:data`,
  `:core:pdf`, `:core:designsystem`, `:feature:contrato/documentacion/revision/firma`.
- Catálogo de versiones centralizado (`gradle/libs.versions.toml`) — una sola
  fuente de verdad para todas las dependencias.
- Hilt cableado de punta a punta: `RellenadorApplication` → `NetworkModule`,
  `DatabaseModule`, `DataStoreModule`, `RepositoryModule`, `AppConfigModule`.
- `Theme.kt` + `Color.kt` + `Shape.kt` + `Type.kt` con la paleta MásOrange y
  los radios/tipografía M3 Expressive **ya validados en el mockup visual**
  (mismos valores hex, no una paleta nueva).
- Contratos de dominio (`PdfRepository`, `AiProxyRepository`, `LocalStoreRepository`,
  `UserPreferencesRepository`) y sus implementaciones reales en `core:data`,
  con la lógica de negocio marcada `TODO Fase 2/4` explícitamente.
- `AiProxyApi` (Retrofit) con el contrato EXACTO de `ai-proxy.php` — no se
  ha tocado el backend, esto es solo el cliente.
- Room (`AppDatabase` + 3 entidades + 3 DAO) sustituyendo a los 3 `localStorage`
  reales, y DataStore sustituyendo a las claves sueltas (tema, modo UE, consentimiento).
- `MainActivity` con `NavHost` de 4 rutas y `WizardNavigationBar` — navegable
  ya mismo entre los 4 placeholders de pantalla.
- `local.properties.sample` + `.gitignore` — patrón `.env` para Android, sin
  ninguna credencial en el código (las claves de IA siguen solo en
  `ai-proxy.config.php`, en el servidor, como siempre).

## Qué NO se ha hecho (a propósito, es de fases siguientes)

- Ninguna lógica de negocio real: extracción IA, validaciones DNI/NIE/CIF/IBAN,
  relleno de AcroForm, fusión de resultados → **Fase 2**.
- Ninguna pantalla real (solo placeholders con icono + texto) → **Fase 3**.
- Firma manuscrita, importar/mejorar foto, selector de páginas, vista previa → **Fase 4**.
- El `.jar` binario de Gradle Wrapper **no se incluye** — no hay `gradle`
  instalado en este entorno de trabajo para generarlo. Primer paso al abrir
  el proyecto: `gradle wrapper --gradle-version 8.9` una vez (o Android
  Studio lo resuelve solo al detectar `gradle/wrapper/gradle-wrapper.properties`
  sin el jar). No se ha compilado ni ejecutado nada aquí — no hay SDK de
  Android ni toolchain de Kotlin en este sandbox, ver README §Validación.
- No se han generado los `.ttf` de Space Grotesk — `Type.kt` usa
  `FontFamily.Default` como fallback con un `TODO` explícito.

## Decisiones tomadas (y por qué)

| Decisión | Motivo |
|---|---|
| `applicationId = com.mejoresiagratis.rellenadorpdv` | Asunción razonable a partir del dominio — nunca se confirmó explícitamente. Cambiar es un `find & replace` de paquete, barato ahora, caro más adelante. |
| `minSdk = 26` | `java.time` nativo sin desugaring, `Canvas`/`CameraX` cómodos, cubre el rango Honor 400 → Galaxy Ultra citado en la guía original. |
| PdfBox-Android aislado en `:core:pdf` | Si hay que cambiar a iText 7 en el futuro, el cambio no se propaga a `core:data` ni a las features (auditoría §06). |
| `PdfBoxEngine` y `PdfRepositoryImpl` como `@Singleton` | Un contrato de 54 páginas no puede vivir duplicado en memoria — mismo criterio que pide la Fase 1 del plan de ruta. |
| Firmas guardadas: ruta de archivo en Room, no BLOB | Ya se señaló en la auditoría (§04.8); un PNG de firma no debe vivir dentro de una fila de SQLite. |
| `BuildConfig` leído solo en `:app` (`AppConfigModule`) | `core:data` no puede depender de `:app` sin crear un ciclo — se expone la URL del proxy vía `@Provides` en el único módulo que sí conoce `BuildConfig`. |
| Rol "éxito" (verde de validación) como `CompositionLocal` aparte | M3 no lo trae de serie; forzarlo dentro de `ColorScheme` competiría con el esquema oficial si una versión futura de M3 lo incorpora. |

## Preguntas abiertas (no bloquean, pero hay que confirmarlas antes de Fase 2)

1. ¿`applicationId` definitivo, o vale el propuesto?
2. ¿El `contrato-base.pdf` va empaquetado en `assets/` o se sigue descargando
   de `BASE_CONTRACT_URL` como hace hoy la web? Ahora mismo el scaffold deja
   la URL lista en `local.properties.sample` pero no descarga nada todavía.
3. ¿El repo Android nace aparte o como `/android` dentro de
   `smart-pdf-autofiller`? Este ZIP está preparado para encajar como
   carpeta `android/` en la raíz de cualquiera de los dos.

---

# Archivos Implicados y Efectos Colaterales

Todo lo listado abajo es **código nuevo** en un proyecto Android que no
existía — cero archivos del proyecto web (`rellenador-pro.html`, `ai-proxy.php`,
`.htaccess`, `contrato-base.pdf`) han sido tocados, movidos ni borrados.
No hay "efecto colateral" sobre código previo porque no hay código previo
en este repositorio: es la primera entrega.

**Módulos creados:** `app`, `core/domain`, `core/data`, `core/pdf`,
`core/designsystem`, `feature/contrato`, `feature/documentacion`,
`feature/revision`, `feature/firma`.

**Puntos de extensión ya preparados para Fase 2** (para que quien continúe
sepa exactamente dónde escribir, sin tener que rediseñar nada):

- `core/domain/.../usecase/UseCases.kt` — 7 `TODO()` con la firma ya fijada.
- `core/pdf/.../PdfBoxEngine.kt` — 4 métodos comentados como próximos.
- `core/data/.../repository/PdfRepositoryImpl.kt` — `loadDocument()` y
  `buildFilledBytes()` marcados explícitamente.

Cualquier cambio futuro sobre estos archivos debe respetar las firmas ya
definidas aquí — son el contrato que ya conocen `core:data` y las features;
cambiarlas en Fase 2 sin avisar rompería la compilación de los módulos que
ya dependen de ellas.
