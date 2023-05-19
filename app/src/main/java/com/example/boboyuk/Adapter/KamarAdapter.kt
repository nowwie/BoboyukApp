package com.example.boboyuk.Adapter

import com.example.boboyuk.Entity.User
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boboyuk.Entity.Kamar
import com.example.boboyuk.R

class KamarAdapter(var list: List<Kamar>) : RecyclerView.Adapter<KamarAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var Kamar: TextView
        var idTyp: TextView

        init {
            Kamar = view.findViewById(R.id.kamar)
            idTyp = view.findViewById(R.id.typ)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kamar, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Kamar.text = list[position].NameKamar
        holder.idTyp.text = list[position].idTyp
    }
}
