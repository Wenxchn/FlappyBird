package com.example.flappybird

import android.content.res.Resources
import android.graphics.BitmapFactory

class PipeTop (res: Resources) {
    var x = 1200
    var y = -600
    var pipeTopImage = BitmapFactory.decodeResource(res, R.drawable.cot_top)
}