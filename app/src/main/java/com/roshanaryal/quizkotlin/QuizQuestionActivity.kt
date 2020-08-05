package com.roshanaryal.quizkotlin

import android.animation.Animator
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var currentPosition: Int = 1
    private var questionList: ArrayList<MyQuestion>? = null
    private var mslectedOptionPosition: Int = 0
    private var correctAnswerCount: Int = 0
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        questionList = Constants.getQuestion();
        progress_horizontal.max = questionList!!.size



        setQuestion()
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)




        btn_submit_next.setOnClickListener(this)


    }

    private fun setQuestion() {
        val question: MyQuestion? = questionList!![currentPosition - 1]
        defaultOptionView()

        if (currentPosition == questionList!!.size) {
            btn_submit_next.text = "Finish"
        } else {
            btn_submit_next.text = "Submit"

        }
        makeViewDisableAndEnable(false)
        playAnim(tv_question, 0, question!!.question, question)


        // progress_horizontal.progress = currentPosition
        // tv_progress.text = "$currentPosition/${questionList!!.size}"
        //progress_horizontal.progress = currentPosition;
        /*  val textArr=ArrayList<TextView>()
          textArr.add(tv_question);
          textArr.add(tv_option_one);
          textArr.add(tv_option_two);
          textArr.add(tv_option_three);
          textArr.add(tv_option_four);

          for (tv in textArr){
              tv.animate().alpha(value.toFloat()).scaleX(value.toFloat()).scaleY(value.toFloat())
                  .setDuration(100).setStartDelay(100)
                  .setInterpolator(DecelerateInterpolator())
          }

          tv_question.text = question!!.question
          tv_option_one.text = question.optionOne
          tv_option_three.text = question.optionThree
          tv_option_two.text = question.optionTwo
          tv_option_four.text = question.optionFour*/


    }

    private fun defaultOptionView() {
        val option = ArrayList<TextView>()
        option.add(0, tv_option_one)
        option.add(1, tv_option_two)
        option.add(2, tv_option_three)
        option.add(3, tv_option_four)

        for (o in option) {
            o.setTextColor(Color.parseColor("#7A8089"))
            o.typeface = Typeface.DEFAULT
            o.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.tv_option_one -> {
                slectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two -> {
                slectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three -> {
                slectedOptionView(tv_option_three, 3)
            }

            R.id.tv_option_four -> {
                slectedOptionView(tv_option_four, 4)
            }

            R.id.btn_submit_next -> {

                if (mslectedOptionPosition == 0) {
                    currentPosition++
                    when {
                        currentPosition <= questionList!!.size -> {
                            count = 0
                            setQuestion()
                            //currentPosition++
                        }
                        else -> {
                            Toast.makeText(this, "finished", Toast.LENGTH_SHORT).show();
                            val i = Intent(this, ResultActivity::class.java)
                            i.putExtra("score", correctAnswerCount)
                            startActivity(i)
                        }
                    }
                } else {
                    val question = questionList!![currentPosition - 1]
                    if (question.correctAnswer != mslectedOptionPosition) {
                        answerview(mslectedOptionPosition, R.drawable.incorrect_option_border_bg);

                    } else {
                        correctAnswerCount++;
                    }
                    answerview(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (currentPosition == questionList!!.size) {
                        btn_submit_next.text = "Finish"
                    } else {
                        btn_submit_next.text = "Go To Next Question"

                    }
                    makeViewDisableAndEnable(true)
                    mslectedOptionPosition = 0
                }


            }

        }

    }

    private fun answerview(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun slectedOptionView(tv: TextView, slectedOptionPosition: Int) {

        defaultOptionView()
        mslectedOptionPosition = slectedOptionPosition
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.slected_option_bg)
    }


    private fun playAnim(
        view: View,
        value: Int,
        data: String
        , q: MyQuestion
    ) {

        view.animate().alpha(value.toFloat()).scaleX(value.toFloat()).scaleY(value.toFloat())
            .setDuration(100).setStartDelay(100)
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    if (value == 0 && count < 6) {

                        var text = ""
                        var view: View? = null
                        if (count == 0) {
                            text = "$currentPosition/${questionList!!.size}"
                            view = tv_progress
                        } else if (count == 1) {
                            text = ""
                            view = progress_horizontal
                        } else if (count == 2) {
                            text = q!!.optionOne
                            view = tv_option_one

                        } else if (count == 3) {
                            text = q!!.optionTwo
                            view = tv_option_two
                        } else if (count == 4) {
                            text = q!!.optionThree
                            view = tv_option_three
                        } else if (count == 5) {
                            text = q!!.optionFour
                            view = tv_option_four
                        }

                        if (view != null) {
                            playAnim(view, 0, text, q)
                        }
                        count++
                    }
                }

                override fun onAnimationEnd(animation: Animator) { //change data here and make view visibe
                    if (value == 0) {

                        if (view==progress_horizontal) {
                            progress_horizontal.progress = currentPosition

                        }else {
                            try {
                                (view as TextView).text = data
                                // tv_progress.text="$currentPosition/"
                            } catch (e: Exception) {

                                //view.tag = data
                            }
                        }
                        playAnim(view, 1, data, q)
                    }


                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
    }

    private fun makeViewDisableAndEnable(boolen: Boolean) {
        if (boolen) {
            tv_option_one.setOnClickListener(null)
            tv_option_two.setOnClickListener(null)
            tv_option_three.setOnClickListener(null)
            tv_option_four.setOnClickListener(null)
        } else {
            tv_option_one.setOnClickListener(this)
            tv_option_two.setOnClickListener(this)
            tv_option_three.setOnClickListener(this)
            tv_option_four.setOnClickListener(this)
        }
    }
}
