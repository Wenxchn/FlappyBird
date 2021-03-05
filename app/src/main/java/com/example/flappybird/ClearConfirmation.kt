package com.example.flappybird

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class ClearConfirmation : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.clear_confirmation, container, false)

        rootView.findViewById<Button>(R.id.confirmClear).setOnClickListener {
            deleteScores(it)
            dismiss()
        }

        return rootView
    }

    private fun deleteScores(v: View) {
        val preference = activity?.getSharedPreferences("scores", Context.MODE_PRIVATE)
        preference?.edit()?.clear()?.apply()
    }
}