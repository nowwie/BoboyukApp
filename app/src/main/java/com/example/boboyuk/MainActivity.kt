package com.example.boboyuk

import com.example.boboyuk.User.UserActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.boboyuk.Kamar.KamarActivity
import com.example.boboyuk.TipeKmr.TipeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataUser: Button = findViewById(R.id.buttonUser)
        val tipeKmr: Button = findViewById(R.id.buttonTipe)
        val kamar: Button = findViewById(R.id.buttonKmr)

        dataUser.setOnClickListener{
            val moveUser = Intent(this@MainActivity, UserActivity::class.java)
            startActivity(moveUser)
        }

        tipeKmr.setOnClickListener {
            val moveTip = Intent ( this@MainActivity, TipeActivity::class.java)
            startActivity(moveTip)
        }

        kamar.setOnClickListener {
            val moveKmr = Intent (this@MainActivity, KamarActivity::class.java)
            startActivity(moveKmr)
        }

    }
}