package com.example.flappybird

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.*
import android.os.Handler
import android.preference.PreferenceManager
import android.view.ContextThemeWrapper
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import java.util.*


class GameView(context: Context?, w: Float, h: Float) : View(context) {
    var timer : Timer = Timer()
    var timeHandler : Handler = Handler()
    var fps = 0
    var birdVelocity : Int = 1
    var score : Int = 0

    var bird : Bird = Bird(resources)
    var pipeTop : PipeTop = PipeTop(resources)
    var pipeBot : PipeBot = PipeBot(resources)

    private val gameHeight = h

    private val paint = Paint()

    init {
        val timeTask = object : TimerTask() {
            override fun run() {
                timeHandler.post(Runnable {
                    invalidate()
                    if (hasCollision()) {
                        timer.cancel()
                        var dialog = ScoreInputActivity()
                        dialog.isCancelable = false
                        getFragmentManager(context)?.let { dialog.show(it, "scoreInputDialog") }
                        bird.x = height
                        fps = 0

                        var sharedPref : SharedPreferences  = PreferenceManager.getDefaultSharedPreferences(context)
                        var editor : SharedPreferences.Editor = sharedPref.edit()
                        editor.putString("score", score.toString())
                        editor.apply()


//                        Will ask questions on this later

//                        val intent = Intent(context, ScoreInputActivity::class.java)
//                        intent.putExtra("score", score)
//                        try {
//                            context?.startActivity(intent)
//                        } catch (e: ActivityNotFoundException) {
//
//                        }
                    }
                })
            }
        }
        startTimer(timeTask)
    }

    fun getFragmentManager(context: Context?): FragmentManager? {
        return when (context) {
            is AppCompatActivity -> context.supportFragmentManager
            is ContextThemeWrapper -> getFragmentManager(context.baseContext)
            else -> null
        }
    }

    private fun startTimer(task: TimerTask) {
        timer = Timer()
        timer.schedule(task, 1, 10)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(bird.birdImage, bird.x.toFloat(), bird.y.toFloat(), paint)
        canvas?.drawBitmap(pipeTop.pipeTopImage, pipeTop.x.toFloat(), pipeTop.y.toFloat(), paint)
        canvas?.drawBitmap(pipeBot.pipeBotImage, pipeBot.x.toFloat(), pipeBot.y.toFloat(), paint)

        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.textSize = 80f
        paint.isAntiAlias = true;
        canvas?.drawText(score.toString(), 900f, 100f, paint)

        if (fps > 0) {
            birdVelocity += 1
            bird.y += birdVelocity
            pipeTop.x -= 10
            pipeBot.x -= 10
            if (pipeTop.x <= -300 && pipeBot.x <= -300) {
                pipeTop.x = 1200
                pipeBot.x = 1200
                pipeTop.y = -600
                pipeBot.y = 1400
                val modifier = (100..600).random()
                val upOrDown = (1..2).random()
                if (upOrDown == 1) {
                    pipeTop.y += modifier
                    pipeBot.y += modifier
                } else {
                    pipeTop.y -= modifier
                    pipeBot.y -= modifier
                }
                score++
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.actionMasked) {
            MotionEvent.ACTION_DOWN -> fly()
        }
        return true
    }

    private fun fly() {
        if (fps == 0) {
            fps = 61
        }
        if (bird.y >= 250) {
            bird.y -= 250
            birdVelocity = 1
        }
    }

    fun hasCollision() : Boolean {
        if (bird.y.toFloat() >= gameHeight) {
            return true
        }

        if (pipeTop.x <= 0 || pipeTop.x <= 0) {
            if (bird.y <= pipeTop.y + 1520 &&
                bird.x >= pipeTop.x && bird.x <= pipeTop.x) {
                return true
            }

            if (bird.y >= pipeBot.y - 80 &&
                bird.x >= pipeBot.x && bird.x <= pipeBot.x) {
                return true
            }
        } else {
            if (bird.y <= pipeTop.y + 1520 &&
                bird.x >= pipeTop.x && bird.x <= pipeTop.x + 350) {
                return true
            }

            if (bird.y >= pipeBot.y - 80 &&
                bird.x >= pipeBot.x && bird.x <= pipeBot.x + 350) {
                return true
            }
        }
        return false
    }
}