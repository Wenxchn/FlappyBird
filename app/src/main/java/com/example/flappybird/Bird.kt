package com.example.flappybird

import android.content.res.Resources
import android.graphics.BitmapFactory

class Bird (res: Resources) {
    var x : Int = 100
    var y : Int = 1000
    var birdImage = BitmapFactory.decodeResource(res, R.drawable.bird)
}