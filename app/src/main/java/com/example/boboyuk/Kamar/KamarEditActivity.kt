package com.example.boboyuk.Kamar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.RoomDatabase
import com.example.boboyuk.Database.AppDatabase
import com.example.boboyuk.Entity.Kamar
import com.example.boboyuk.Entity.User
import com.example.boboyuk.R

class KamarEditActivity : AppCompatActivity() {

    private lateinit var Kamar: EditText
    private lateinit var typ: EditText
    private lateinit var database: AppDatabase
    private lateinit var btnSaveKmr: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kamar_edit)
        Kamar = findViewById(R.id.edtKamar)
        typ = findViewById(R.id.edtTyp)
        btnSaveKmr = findViewById(R.id.btn_saveKmr)
        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if (intent != null) {
            var id = intent.getInt("id", 0)
            var kamar = database.BoboyukDao().ambilSemuaKamar(id)

            Kamar.setText(kamar.NameKamar)
            typ.setText(kamar.idTyp)

        }
        btnSaveKmr.setOnClickListener {
            if (Kamar.text.isNotEmpty() && typ.text.isNotEmpty()) {
                if (intent != null) {
                    database.BoboyukDao().updateKamar(
                        Kamar(
                            intent.getInt("id", 0),
                            Kamar.text.toString(),
                            typ.text.toString()
                        )
                    )
                } else {
                    database.BoboyukDao().tambahKamar(
                        Kamar(
                            null,
                            Kamar.text.toString(),
                            typ.text.toString()
                        )
                    )
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Isi yang bener", Toast.LENGTH_SHORT).show()

            }
        }
    }
}