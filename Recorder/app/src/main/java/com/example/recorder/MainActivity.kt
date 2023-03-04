package com.example.recorder

import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.widget.Button
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private val recordButton: RecordButton by lazy{
        findViewById(R.id.recordButton)
    }
    private val resetButton: Button by lazy {
        findViewById(R.id.resetButton)
    }

    private val recordingFilePath: String by lazy {
        "${externalCacheDir?.absolutePath}/recording.3gp"
    }
    private val requiredPermissions = arrayOf(android.Manifest.permission.RECORD_AUDIO)
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null
    private var state = State.BEFORE_RECORDEING
        set(value) {
            field = value
            resetButton.isEnabled = (value==State.AFTER_RECORDING) || (value==State.ON_PLAYING)
            recordButton.updateIconWithState(value)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestAudioPermission()
        initViews()
        bindViews()
        initVariables()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted = requestCode == REQUEST_RECORD_AUDIO_PERMISSION &&
                grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if(!audioRecordPermissionGranted){
            finish()
        }else{

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestAudioPermission(){
        requestPermissions(requiredPermissions, REQUEST_RECORD_AUDIO_PERMISSION)
    }

    fun initViews(){
        recordButton.updateIconWithState(state)
    }

    private fun startRecording(){
        recorder = MediaRecorder().
        apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(recordingFilePath)
            prepare()
        }
        recorder?.start()
        state = State.ON_RECORDING
    }

    private fun stopRecording(){
        recorder?.run{
            stop()
            release()
        }
        recorder = null
        state = State.AFTER_RECORDING
    }

    private fun startPlaying(){
        player = MediaPlayer().apply {
            setDataSource(recordingFilePath)
            prepare()
        }
        state = State.ON_PLAYING
    }

    private fun stopPlaying(){
        player?.release()
        player = null
        state = State.AFTER_RECORDING
    }

    private fun bindViews(){
        recordButton.setOnClickListener{
            when(state){
                State.BEFORE_RECORDEING -> {
                    startRecording()
                }
                State.ON_RECORDING -> {
                    stopRecording()
                }
                State.ON_PLAYING -> {
                    stopPlaying()
                }
                State.AFTER_RECORDING -> {
                    startPlaying()
                }
            }
        }
        resetButton.setOnClickListener {
            stopPlaying()
            state = State.BEFORE_RECORDEING
        }
    }

    private fun initVariables(){
        state = State.BEFORE_RECORDEING
    }

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 201
    }
}