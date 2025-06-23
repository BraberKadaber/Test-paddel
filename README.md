# Paddelrutt-app

Det här projektet är nu ett enkelt Android-projekt som visar samma kartfunktioner i en WebView. Du kan rita ut din paddelrutt och se den totala sträckan.

## Kom igång (Webbversion)

Öppna `app/src/main/assets/index.html` i din webbläsare. Klicka på kartan för att sätta ut punkter på din rutt. Varje punkt läggs till i en linje och den totala sträckan visas under kartan. Du kan återställa rutten med knappen "Återställ".

Applikationen använder [Leaflet](https://leafletjs.com/) och tile-data från OpenStreetMap.

## Bygg Android-applikationen

1. Se till att du har Android Studio eller Android SDK och Gradle installerat.
2. Kör `gradle assembleDebug` i projektets rotmapp för att bygga en debugversion av appen.
3. Installera APK-filen som genereras i `app/build/outputs/apk/debug` på din Android-enhet.
