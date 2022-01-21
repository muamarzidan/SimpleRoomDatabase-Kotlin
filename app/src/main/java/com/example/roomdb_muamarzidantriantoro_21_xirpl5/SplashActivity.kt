package com.example.roomdb_muamarzidantriantoro_21_xirpl5

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

        private lateinit var splash: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)

            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

            val fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.stb)


            splash = findViewById(R.id.splash)

            splash.startAnimation(fadeAnimation)

            val splashTo = 3000
            val homeIntent = Intent(this,Dashbord::class.java)

            Handler().postDelayed({
                startActivity(homeIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                finish()
            }, splashTo.toLong())
        }
    }