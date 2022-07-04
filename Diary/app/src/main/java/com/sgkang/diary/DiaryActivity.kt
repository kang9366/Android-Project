package com.sgkang.diary

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity:AppCompatActivity() {
    private val diaryEditText: EditText by lazy{
        findViewById<EditText>(R.id.diaryEditText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)
        diaryEditText.setText(detailPreferences.getString("detail", ""))

        val runnable = Runnable{
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit(false) {

            }
        }

        //앱이 갑작스럽게 종료될 때에도 text를 저장하기 위해서 text가 수정될 떄마다 저장
        diaryEditText.addTextChangedListener {
            detailPreferences.edit {
                putString("detail", diaryEditText.text.toString())
            }
        }
    }
}