package com.example.boboyuk.User

import com.example.boboyuk.Adapter.UserAdapter
import com.example.boboyuk.Database.AppDatabase
import com.example.boboyuk.Entity.User
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
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.boboyuk.MainActivity
import com.example.boboyuk.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        recyclerView = findViewById(R.id.listMyUser)
        fab = findViewById(R.id.fabAddUser)

        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@UserActivity)
                dialog.setTitle(list[position].Name)
                dialog.setItems(R.array.popup,DialogInterface.OnClickListener({dialog, which ->
                    if (which == 0){
                        val intent = Intent(this@UserActivity, UserEditorActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    } else if(which == 1){
                        database.BoboyukDao().deleteUser(list[position])
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
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener {
            startActivity(Intent(this, UserEditorActivity::class.java))
        }
        btnBack = findViewById(R.id.backUser)
        btnBack.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        }
    override fun onResume(){
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.BoboyukDao().getAll())
        adapter.notifyDataSetChanged()
    }
}
