package com.association.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.association.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            var main: Intent = Intent(this@MainActivity, LanguageSelect::class.java)
            startActivity(main)
            finish()
        }, 2500)

    }
}
