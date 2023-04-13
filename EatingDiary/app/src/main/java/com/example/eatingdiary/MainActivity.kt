package com.example.eatingdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.eatingdiary.Model.Data
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        textView = findViewById(R.id.textView)

        button.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.apply {
                setPrompt("바코드 스캔")
                setCameraId(0)
                setBeepEnabled(true)
                setBarcodeImageEnabled(false)
                initiateScan()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents==null){
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }else{
                sendData("${result.contents}")
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun sendData(barcode: String){
        Toast.makeText(this, barcode, Toast.LENGTH_SHORT).show()
        val call = RetrofitBuilder.api.getFoodName(barcode)
        call.enqueue(object : retrofit2.Callback<Data> {
            override fun onResponse(call: retrofit2.Call<Data>, response: Response<Data>) {
                val foodName = response.body()
                if(response.isSuccessful){
                    if (foodName != null) {
                        Log.d("김기류", response.message())
                        textView.text = "${foodName.C005.row[0].PRDLST_NM}"
                    }
                }else{
                    Log.d("Failure", "실패")
                }
            }

            override fun onFailure(call: retrofit2.Call<Data>, t: Throwable) {
                Log.d("Failure", "${t.message}")
            }
        })
    }
}