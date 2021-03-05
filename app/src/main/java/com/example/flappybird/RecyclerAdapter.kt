package com.example.flappybird

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (private var scores: List<Score>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val sortedList = listOf<Score>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val score : TextView = itemView.findViewById(R.id.scoreName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.score_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var text = scores[position].toString()
        var nameText = text.substring(text.indexOf("=") + 1, text.indexOf(" ") - 1)
        var scoreText = text.substring(text.indexOf("=") + 1)
        scoreText = scoreText.substring(scoreText.indexOf("=") + 1, scoreText.indexOf(")"))
        holder.score.text = "Name: " + nameText + " | Score: "+ scoreText
    }

    override fun getItemCount(): Int {
        return scores.size
    }


}