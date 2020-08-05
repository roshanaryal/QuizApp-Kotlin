package com.roshanaryal.quizkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN

        btn_submit.setOnClickListener {
            if (et_getname.text.toString().isEmpty()){
                et_getname.setError("Please enter name")
                Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show()
            }
            else{
               val i=Intent(this,QuizQuestionActivity::class.java)
                startActivity(i)
                finish()

            }
        }

    }
}
