package com.example.flappybird

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class ScoreInputActivity : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.activity_score_input, container, false)
        rootView.findViewById<Button>(R.id.cancelName).setOnClickListener {
            dismiss()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        rootView.findViewById<Button>(R.id.submitName).setOnClickListener {
            saveScore(rootView)
        }
        return rootView
    }

    private fun saveScore(v: View) {

        var sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val score : String? = sharedPref.getString("score", "0")

//        Will ask questions about this later

//        val score = activity?.intent?.getStringExtra("score")

        var name = v.findViewById<EditText>(R.id.nameInput).text
        val preference = activity?.getSharedPreferences("scores", Context.MODE_PRIVATE)
        var allNames = preference?.getString("names", "")
        val editor = preference?.edit()

        if (allNames!!.isEmpty()) {
            allNames += name
        } else {
            allNames +="|$name"
        }

        editor?.putString("names", allNames)
        val highScore = buildString {
            append(name)
            append("_score")
        }

        editor?.putString(highScore, score)
        editor?.apply()
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }
}