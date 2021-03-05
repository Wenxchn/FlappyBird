package com.example.flappybird

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.startButton).setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.leaderButton).setOnClickListener {
            val intent = Intent(this, LeaderBoardActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.clearButton).setOnClickListener {
//            deleteScores(it)
            var dialog = ClearConfirmation()
            dialog.show(supportFragmentManager, "clearConfirmationDialog")
        }
    }



//    private fun deleteScores(v: View) {
//        val preference = getSharedPreferences("scores", Context.MODE_PRIVATE)
//        preference.edit().clear().apply()
//    }
}