package com.example.boboyuk.User

import android.app.Activity
import com.example.boboyuk.Database.AppDatabase
import com.example.boboyuk.Entity.User
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.boboyuk.R

class UserEditorActivity : AppCompatActivity() {
    private  lateinit var namaUser: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var spinner: Spinner
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase
    private var jobInput = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_editor)

        namaUser = findViewById(R.id.edtNamaUser)
        username = findViewById(R.id.edtUsername)
        password = findViewById(R.id.edtPassword)
        spinner = findViewById(R.id.edtJobdesk)
        btnSave = findViewById(R.id.btn_save)
        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if (intent != null) {
            var id = intent.getInt("id", 0)
            var user = database.BoboyukDao().ambilSemuaUser(id)

            namaUser.setText(user.Name)
            username.setText(user.Username)
            password.setText(user.Password)

            ArrayAdapter.createFromResource(
                this,
                R.array.jobdesk,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    jobInput = spinner.selectedItem.toString()

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }


           }
            btnSave.setOnClickListener {
                if (namaUser.text.isNotEmpty() && username.text.isNotEmpty() && password.text.isNotEmpty()) {
                    if (intent != null) {
                        database.BoboyukDao().updateUser(
                            User(
                                intent.getInt("id", 0),
                                namaUser.text.toString(),
                                username.text.toString(),
                                password.text.toString(),
                                jobInput
                            )
                        )
                    } else {
                        database.BoboyukDao().tambahUser(
                            User(
                                null,
                                namaUser.text.toString(),
                                username.text.toString(),
                                password.text.toString(),
                                jobInput
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
