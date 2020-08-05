package com.roshanaryal.quizkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var score=intent.getIntExtra("score",0)
        result_tv.text= "Congratulation !!!\nYour score is " + score
    }
}
