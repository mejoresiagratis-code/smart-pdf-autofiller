# Rellenador PdV · Android nativo

App nativa Kotlin + Jetpack Compose + Material 3 Expressive, migración de
`rellenador-pro.html` (ver `docs/FASE_1_ENTREGA.md` para el detalle de esta
entrega y el informe de auditoría original para el análisis completo).

## Abrir el proyecto

1. Copia `local.properties.sample` → `local.properties` y ajusta `sdk.dir`
   a tu instalación de Android SDK (Android Studio lo rellena solo si abres
   el proyecto desde ahí).
2. Abre la carpeta raíz con Android Studio (Ladybug o posterior — necesita
   soporte de AGP 8.7 / Kotlin 2.0).
3. Si Android Studio pide generar el Gradle Wrapper (no se incluye el
   `.jar` binario en este entregable, ver más abajo), acepta — o desde
   terminal, con `gradle` instalado localmente:
   ```
   gradle wrapper --gradle-version 8.9
   ```

## Validación — qué se ha comprobado y qué no

Este scaffold se ha preparado en un entorno sin SDK de Android, sin Gradle
y sin `kotlinc` instalados — **no se ha compilado aquí**. Se ha revisado a
mano: consistencia de paquetes/imports entre módulos, dependencias del
catálogo de versiones referenciadas con el accessor correcto
(`libs.grupo.artefacto`), y que cada `build.gradle.kts` declara exactamente
los módulos internos de los que depende.

**Primer paso real de validación, en tu máquina:**
```
./gradlew :app:assembleDebug
```
Si algo no compila, es información nueva y útil — este README se puede
actualizar con lo que falle. No se ha fingido ninguna compilación exitosa.

## Estructura

```
android/
├── app/                      punto de entrada, DI raíz, navegación de 4 pasos
├── core/
│   ├── domain/                Kotlin puro — modelos, UseCase, contratos de repositorio
│   ├── data/                  Retrofit + Room + DataStore, implementaciones reales
│   ├── pdf/                   único módulo que conoce PdfBox-Android
│   └── designsystem/          Theme.kt, paleta MásOrange, componentes compartidos
├── feature/
│   ├── contrato/               paso 1 (placeholder — Fase 3)
│   ├── documentacion/          paso 2 (placeholder — Fase 3)
│   ├── revision/                paso 3 (placeholder — Fase 3)
│   └── firma/                   paso 4 (placeholder — Fase 4)
└── docs/
    └── FASE_1_ENTREGA.md        detalle de esta entrega, decisiones y preguntas abiertas
```

## Seguridad — nunca hardcodear credenciales

- `local.properties` está en `.gitignore` — nunca se sube.
- Las claves de los 9 proveedores de IA **no viven en la app**, nunca han
  vivido ni deben vivir. Siguen solo en `ai-proxy.config.php`, en el
  servidor — la app solo conoce la URL pública del proxy.
- Si en algún momento un token o clave se pega por error en un chat o un
  commit, se da por comprometido y se revoca — no se reutiliza.

## Siguiente fase

Fase 2 — Motor de autorelleno y validaciones, en `core/domain` +
`core/pdf`, con tests JUnit antes de tocar ninguna pantalla. Ver
`docs/FASE_1_ENTREGA.md` → "Puntos de extensión ya preparados para Fase 2".
