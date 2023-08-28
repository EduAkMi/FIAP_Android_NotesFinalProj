package com.notesfinalproject.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.notesfinalproject.R
import com.notesfinalproject.presentation.login.LoginActivity
import com.notesfinalproject.presentation.notes.NotesActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val delayWait = 300L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Runnable {
            Handler(Looper.getMainLooper()).postDelayed({
                val activity = when (FirebaseAuth.getInstance().currentUser == null) {
                    true -> LoginActivity::class.java
                    false -> NotesActivity::class.java
                }

                val intent = Intent(this, activity)
                finish()
                startActivity(intent)
            }, delayWait)
        }.run()
    }
}