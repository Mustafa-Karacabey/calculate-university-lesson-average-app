package com.example.calculate_university_lesson_average_app

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var anim = AnimationUtils.loadAnimation(this, R.anim.up_to_bottom_anim)
        var secondAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_to_up_anim)

        button_anim.animation = secondAnim
        bln_image.animation = anim

        button_anim.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java) //Main Activitye dönmek istediğimi söyleyen intenti belirledik
            startActivity(intent)

            object : CountDownTimer(1000,1000) {
                override fun onTick(p0: Long) {
                    TODO("Not yet implemented")
                }

                override fun onFinish() {

                }

            }.start()

        }

    }
}