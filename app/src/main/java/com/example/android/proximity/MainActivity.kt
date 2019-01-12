package com.example.android.proximity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vManager=getSystemService(Context.VIBRATOR_SERVICE)as Vibrator
        val pManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val s: Sensor = pManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        pManager.registerListener(object : SensorEventListener {
            override fun onSensorChanged(pos: SensorEvent?) {
                val values = pos?.values
                if (values?.get(0)==0f)
                {
                    vManager.vibrate(3000L)
                }
                textView.text = "${values!![0]}"
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }, s, SensorManager.SENSOR_DELAY_NORMAL)

      }
}