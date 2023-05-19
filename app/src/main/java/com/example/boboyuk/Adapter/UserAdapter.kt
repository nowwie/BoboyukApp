package com.example.boboyuk.Adapter

import com.example.boboyuk.Entity.User
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boboyuk.R

class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var namaUser: TextView
        var username: TextView
        var password: TextView
        var jobdesk : TextView

        init {
            namaUser = view.findViewById(R.id.namaUser)
            username = view.findViewById(R.id.username)
            password = view.findViewById(R.id.password)
            jobdesk = view.findViewById(R.id.jobdesk)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaUser.text = list[position].Name
        holder.username.text = list[position].Username
        holder.password.text = list[position].Password
        holder.jobdesk.text = list[position].Job
    }
}
