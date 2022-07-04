package com.sgkang.diary

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1).apply {
            minValue = 0
            maxValue = 9
        }
    }
    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2).apply {
            minValue = 0
            maxValue = 9
        }
    }
    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3).apply {
            minValue = 0
            maxValue = 9
        }
    }

    private val openButton: AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.openButton)
    }
    private val changePasswordButton: AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }

    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {
            if(changePasswordMode){
                Toast.makeText(this, "비밀번호 변경중입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser:String = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
            Toast.makeText(this, passwordFromUser, Toast.LENGTH_SHORT).show()
            if(passwordPreferences.getString("password", "000").equals(passwordFromUser)){
                startActivity(Intent(this, DiaryActivity::class.java))
            }else{
                showErrorAlertDialog()
            }
        }

        changePasswordButton.setOnClickListener {
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser:String = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if(changePasswordMode){
                //비밀번호 변경모드가 실행된 상태에서 비밀번호 변경을 누르면 비밀번호 변경 모드 끄기
                passwordPreferences.edit(true) {
                    putString("password", passwordFromUser)
                }
                Toast.makeText(this, "비밀번호 변경 완료", Toast.LENGTH_SHORT).show()
                changePasswordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)
            }else{
                if(passwordPreferences.getString("password", "000").equals(passwordFromUser)){
                    changePasswordMode = true
                    Toast.makeText(this, "변경할 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                    //비밀번호 변경 모드가 실행되었음을 나타내기위해 비밀번호 변경 버튼의 색상 변화주기
                    changePasswordButton.setBackgroundColor(Color.RED)
                }else{
                    Toast.makeText(this, "비밀번호 오류", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun showErrorAlertDialog(){
        AlertDialog.Builder(this)
            .setTitle("실패")
            .setMessage("비밀번호 오류")
            .setPositiveButton("확인"){_, _ ->}.show()
    }
}