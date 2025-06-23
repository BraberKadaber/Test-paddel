package com.example.paddel.safety

import android.util.Log
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

/**
 * Service responsible for fetching basic wind and water information from SMHI's
 * open data APIs.  The method is intentionally lightweight and merely returns a
 * pair of values representing the latest available wind speed and water level
 * for the supplied position.
 */
class SafetyService @Inject constructor() {

    data class SmhiData(val windSpeed: Double?, val waterLevel: Double?)

    /**
     * Fetch the current wind and water data for the provided coordinates.  The
     * implementation uses simple blocking network calls and the built-in JSON
     * parser so that no extra dependencies are required.
     */
    fun fetchSmhiData(lat: Double, lon: Double): SmhiData {
        val wind = try {
            val forecastUrl =
                "https://opendata.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/$lon/lat/$lat/data.json"
            val json = httpGet(forecastUrl)
            parseWind(json)
        } catch (e: Exception) {
            Log.w("SafetyService", "Failed to load wind data", e)
            null
        }

        val water = try {
            // Example station for water level, real apps should choose nearest
            val stationUrl =
                "https://opendata.smhi.se/api/category/hydrography/version/1.0/parameter/wa/station/1200/period/latest-months/data.json"
            val json = httpGet(stationUrl)
            parseWater(json)
        } catch (e: Exception) {
            Log.w("SafetyService", "Failed to load water data", e)
            null
        }

        return SmhiData(wind, water)
    }

    private fun httpGet(url: String): String {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.connectTimeout = 10_000
        connection.readTimeout = 10_000
        return connection.inputStream.bufferedReader().use { it.readText() }
    }

    private fun parseWind(json: String): Double? {
        val root = JSONObject(json)
        val timeSeries = root.getJSONArray("timeSeries")
        if (timeSeries.length() == 0) return null
        val first = timeSeries.getJSONObject(0)
        val params = first.getJSONArray("parameters")
        for (i in 0 until params.length()) {
            val p = params.getJSONObject(i)
            if (p.getString("name") == "ws") {
                return p.getJSONArray("values").getDouble(0)
            }
        }
        return null
    }

    private fun parseWater(json: String): Double? {
        val root = JSONObject(json)
        val values = root.getJSONArray("value")
        if (values.length() == 0) return null
        return values.getJSONObject(0).getDouble("value")
    }
}
