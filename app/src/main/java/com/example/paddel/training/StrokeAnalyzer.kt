package com.example.paddel.training

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Very small helper that registers a listener on the linear acceleration
 * sensor and keeps track of detected paddle strokes.  A stroke is counted when
 * the measured acceleration exceeds a threshold and enough time has passed
 * since the last detected stroke.  The algorithm is intentionally simple but
 * serves to demonstrate how motion sensor input could be processed.
 */
class StrokeAnalyzer @Inject constructor(
    @ApplicationContext context: Context
) : SensorEventListener {

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    var strokeCount: Int = 0
        private set

    private var lastPeakTime: Long = 0

    /** Threshold for acceleration magnitude to be considered a stroke. */
    private val threshold = 11f

    /**
     * Start analysing paddle strokes.  Each time a stroke is detected the
     * [strokeCount] property is incremented.  Callers may poll this property or
     * extend the class to provide callbacks when a new stroke is registered.
     */
    fun analyze() {
        sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        val magnitude = sqrt(
            event.values[0].pow(2) + event.values[1].pow(2) + event.values[2].pow(2)
        )
        val now = System.currentTimeMillis()
        if (magnitude > threshold && now - lastPeakTime > 300) {
            strokeCount++
            lastPeakTime = now
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No-op
    }
}
