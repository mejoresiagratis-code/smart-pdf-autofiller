# Cómo aplicar la Fase 2 desde el móvil (app de GitHub)

No he podido pushear yo directamente: el conector de GitHub tiene lectura
pero la escritura da `403` (y va y viene entre turnos). Así que lo subes tú.
Estás en móvil, así que aquí va el flujo con la **app de GitHub**.

## ⚠️ IMPORTANTE — dos ubicaciones distintas

Este ZIP contiene DOS cosas que van a sitios diferentes del repo:

1. Todo lo que cuelga de `android/…` → va **dentro de la carpeta `android/`**
   del repo (sobrescribiendo lo que coincida).
2. La carpeta `.github/workflows/android.yml` → va en la **RAÍZ del repo**
   (al mismo nivel que `android/`, NO dentro de `android/`). GitHub solo
   ejecuta los workflows si están en `.github/workflows/` en la raíz.

## Pasos con la app de GitHub (móvil)

La app de GitHub para móvil permite editar y crear archivos, pero subir
muchos ficheros de golpe es incómodo. Dos opciones:

**Opción rápida (recomendada): súbelo desde github.com en el navegador del
móvil** (la versión web sí deja arrastrar/seleccionar varios archivos):
1. Abre `github.com/mejoresiagratis-code/smart-pdf-autofiller` en el navegador.
2. Ponlo en "Modo escritorio" si tu navegador lo permite (facilita el "Add file → Upload files").
3. Navega a la carpeta destino (p. ej. `android/core/domain/…/usecase/`) y usa **Add file → Upload files** para subir los `.kt` de esa carpeta. Repite por carpeta.
4. Para el workflow: en la raíz, **Add file → Create new file**, escribe la ruta `.github/workflows/android.yml` (los `/` van creando carpetas) y pega el contenido.
5. **Borra el fichero viejo** que ya no existe: navega a
   `android/core/domain/src/main/java/com/mejoresiagratis/rellenadorpdv/domain/usecase/UseCases.kt`
   → icono papelera → commit.
6. Confirma cada subida con un commit (puedes commitear directo a `main`).

**Opción con la app de GitHub:** funciona igual pero archivo por archivo
(Create new file / editar), más tedioso. Si son muchos, usa la web.

## Qué pasa al hacer commit

En cuanto el `android.yml` esté en `.github/workflows/` y hagas push a
`main`, se dispara **Android CI** solo:
- Corre los 44 tests de `core:domain`.
- Compila `:app:assembleDebug`.
- Si va bien: en la pestaña **Actions** → el run → **Artifacts** →
  descargas `rellenador-pdv-debug-apk` (la APK).
- Si falla: descargas `informes-fallo` con los reports para diagnosticar.

Puedes seguir todo esto desde la app de GitHub en el móvil (pestaña Actions).

## Contenido del ZIP

- `android/core/domain/…/usecase/` — 6 ficheros nuevos (sustituyen a `UseCases.kt`) + soporte (`CanonicalFieldKeys.kt`, `FieldNameUtils.kt`) + 6 de test (44 tests, verificados en verde con kotlinc local).
- `android/core/pdf/PdfBoxEngine.kt` — reescrito completo.
- `android/core/data/repository/PdfRepositoryImpl.kt` — reescrito, sin NotImplementedError.
- `android/core/domain/build.gradle.kts` y `android/core/pdf/build.gradle.kts` — dependencias añadidas.
- `android/docs/FASE_2_ENTREGA.md` — detalle, decisiones y **riesgos conocidos a mirar si el build falla** (léelo).
- `android/README.md` — actualizado.
- `.github/workflows/android.yml` — CI que compila la APK (va en la RAÍZ del repo).

## Recordatorio de seguridad

Si en algún momento generaste un token para esto y lo pegaste en cualquier
sitio (chat incluido), revócalo en github.com/settings/tokens. El CI de
arriba NO necesita ningún token tuyo: usa el `GITHUB_TOKEN` automático que
GitHub inyecta en cada run.
