package com.example.flappybird

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LeaderBoardActivity : AppCompatActivity() {
    private val scores: ArrayList<Score> = ArrayList<Score>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        retrieveScores()
        findViewById<RecyclerView>(R.id.score_recyclerView).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.score_recyclerView).adapter = RecyclerAdapter(scores)

    }

    // Retrieve from share pref
    private fun retrieveScores() {
        val preference = this.getSharedPreferences("scores", Context.MODE_PRIVATE)
        val names = preference.getString("names", "")

        if (names!!.isNotEmpty()) {
            val namesArr = names.split("|")
            for (name in namesArr) {
                val score = preference.getString(name + "_score", "0")
                val newScore = score?.let { Score(name, it) }
                if (newScore != null) {
                    scores.add(newScore)
                }
            }
        }


    }

}