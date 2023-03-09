package com.example.StepCounter

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.stepcounter.R


class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var stepCountSensor: Sensor
    private lateinit var stepCountView: TextView
    private lateinit var resetButton: Button

    var currentSteps = 0

    @RequiresApi(api = Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stepCountView = findViewById(R.id.stepCountView)
        resetButton = findViewById(R.id.resetButton)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 0)
        }

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        stepCountSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

        resetButton.setOnClickListener {
            currentSteps = 0
            stepCountView.setText(currentSteps.toString())
        }
    }

    public override fun onStart() {
        super.onStart()
        if (stepCountSensor != null) {
            sensorManager!!.registerListener(
                this,
                stepCountSensor,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0f) {
                currentSteps++
                stepCountView!!.text = currentSteps.toString()
            }
        }
    }
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}