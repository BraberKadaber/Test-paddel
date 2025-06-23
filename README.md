# Paddelrutt-app

En enkel Android-app byggd med Jetpack Compose. Du kan rita ut din paddelrutt och se den totala sträckan. Nu finns även grunderna för modern autentisering och onboarding.

## Kom igång (Webbversion)

Öppna `app/src/main/assets/index.html` i din webbläsare. Klicka på kartan för att sätta ut punkter på din rutt. Varje punkt läggs till i en linje och den totala sträckan visas under kartan. Du kan återställa rutten med knappen "Återställ".

Applikationen använder [Leaflet](https://leafletjs.com/) och tile-data från OpenStreetMap.

## Bygg Android-applikationen

1. Se till att du har Android Studio eller Android SDK och Gradle installerat.
2. Kör `./gradlew assembleDebug` i projektets rotmapp för att bygga en debugversion av appen.
3. Installera APK-filen som genereras i `app/build/outputs/apk/debug` på din Android-enhet.

## Karta i appen

Map-skärmen laddar `index.html` i en WebView. Där kan du markera din rutt och se den sammanlagda sträckan direkt.

## Auth & Onboarding

Appen använder Credential Manager för inloggning (lösenord och passkeys). En enkel onboarding-skärm visas första gången appen startas och lagras i DataStore.

## Nordic Safety Overlay

Kodbasen innehåller ett ramverk för att hämta data från SMHI och ViVa som ska kunna visas ovanpå kartan som vind- och vatteninformation.

## Stroke Analysis

En modul för att analysera paddeltag via mobilens sensorer finns som grund. Resultatet kommer att ge feedback på kadens och effektivitet.
