package com.example.boboyuk.Kamar

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boboyuk.Adapter.KamarAdapter
import com.example.boboyuk.Adapter.UserAdapter
import com.example.boboyuk.Database.AppDatabase
import com.example.boboyuk.Entity.Kamar
import com.example.boboyuk.Entity.User
import com.example.boboyuk.MainActivity
import com.example.boboyuk.R
import com.example.boboyuk.User.UserEditorActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class KamarActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<Kamar>()
    private lateinit var adapter: KamarAdapter
    private lateinit var database: AppDatabase
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kamar)
        recyclerView = findViewById(R.id.listKamar)
        fab = findViewById(R.id.fabAddKamar)

        database = AppDatabase.getInstance(applicationContext)
        adapter = KamarAdapter(list)
        adapter.setDialog(object : KamarAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@KamarActivity)
                dialog.setTitle(list[position].NameKamar)
                dialog.setItems(R.array.popup, DialogInterface.OnClickListener({ dialog, which ->
                    if (which == 0){
                        val intent = Intent(this@KamarActivity, KamarEditActivity::class.java)
                        intent.putExtra("id", list[position].kid)
                        startActivity(intent)
                    } else if(which == 1){
                        database.BoboyukDao().deleteKamar(list[position])
                        getData()
                    } else {
                        dialog.dismiss()
                    }
                }))
                val dialogView = dialog.create()
                dialogView.show()
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, RecyclerView.VERTICAL))

        fab.setOnClickListener {
            startActivity(Intent(this, KamarEditActivity::class.java))
        }
        btnBack = findViewById(R.id.backKamar)
        btnBack.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }
    override fun onResume(){
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.BoboyukDao().getKmr())
        adapter.notifyDataSetChanged()


    }
}