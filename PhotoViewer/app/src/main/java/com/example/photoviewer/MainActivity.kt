package com.example.photoviewer

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val addPhotoButton: Button by lazy{
        findViewById(R.id.addPhotoButton)
    }
    private val startButton: Button by lazy{
        findViewById(R.id.startButton)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAddPhotoButton()
        initStartButton()
    }

    private fun initStartButton() {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initAddPhotoButton() {
        addPhotoButton.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    //todo 권한이 부여되었을 때

                }
                shouldShowRequestPermissionRationale(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) -> {

                }
                else -> {
                    requestPermissions()
                }
            }
        }
    }
}