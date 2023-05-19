package com.example.boboyuk.TipeKmr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.boboyuk.Database.AppDatabase
import com.example.boboyuk.Entity.Kamar
import com.example.boboyuk.Entity.Tipe
import com.example.boboyuk.R

class TipeEditorActivity : AppCompatActivity() {
    private lateinit var Namatip: EditText
    private lateinit var deskripsi: EditText
    private lateinit var harga: EditText
    private lateinit var database: AppDatabase
    private lateinit var btnSaveTyp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipe_editor)
        Namatip = findViewById(R.id.edtNmTp)
        deskripsi = findViewById(R.id.edtDesk)
        harga = findViewById(R.id.edtHrg)
        btnSaveTyp = findViewById(R.id.btn_saveTip)
        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if (intent != null) {
            var id = intent.getInt("id", 0)
            var tipe = database.BoboyukDao().ambilSemuaTipe(id)

            Namatip.setText(tipe.NamaTyp)
//            typ.setText(tipe.idTyp)
            deskripsi.setText(tipe.Deskripsi)
            harga.setText(tipe.Harga)
        }

        btnSaveTyp.setOnClickListener {
            if (Namatip.text.isNotEmpty() && deskripsi.text.isNotEmpty()) {
                if (intent != null) {
                    database.BoboyukDao().updateTipe(
                        Tipe(
                            intent.getInt("id", 0),
                            Namatip.text.toString(),
                            deskripsi.text.toString(),
                            harga.text.toString()
                        )
                    )
                } else {
                    database.BoboyukDao().tambahTipe(
                        Tipe(
                            null,
                            Namatip.text.toString(),
                            deskripsi.text.toString(),
                            harga.text.toString()
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