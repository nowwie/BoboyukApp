package com.example.boboyuk.TipeKmr

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
import com.example.boboyuk.Adapter.TipeAdapter
import com.example.boboyuk.Database.AppDatabase
import com.example.boboyuk.Entity.Tipe
import com.example.boboyuk.MainActivity
import com.example.boboyuk.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TipeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<Tipe>()
    private lateinit var adapter: TipeAdapter
    private lateinit var database: AppDatabase
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipe)
        recyclerView = findViewById(R.id.listMyTipe)
        fab = findViewById(R.id.fabAddTipe)
        database = AppDatabase.getInstance(applicationContext)
        adapter = TipeAdapter(list)
        adapter.setDialog(object : TipeAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@TipeActivity)
                dialog.setTitle(list[position].NamaTyp)
                dialog.setItems(R.array.popup, DialogInterface.OnClickListener({ dialog, which ->
                    if (which == 0){
                        val intent = Intent(this@TipeActivity, TipeEditorActivity::class.java)
                        intent.putExtra("id", list[position].tid)
                        startActivity(intent)
                    } else if(which == 1){
                        database.BoboyukDao().deleteTipe(list[position])
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
            startActivity(Intent(this, TipeEditorActivity::class.java))
        }
        btnBack = findViewById(R.id.backTipeKmr)
        btnBack.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

    }
    override fun onResume(){
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.BoboyukDao().getTyp())
        adapter.notifyDataSetChanged()


    }
}