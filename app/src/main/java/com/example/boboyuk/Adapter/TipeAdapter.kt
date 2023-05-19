package com.example.boboyuk.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boboyuk.Entity.Kamar
import com.example.boboyuk.Entity.Tipe
import com.example.boboyuk.R

class TipeAdapter(var list: List<Tipe>) : RecyclerView.Adapter<TipeAdapter.ViewHolder>(){
private lateinit var dialog: Dialog

fun setDialog(dialog: Dialog) {
    this.dialog = dialog
  }
    interface Dialog {
        fun onClick(position: Int)
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var NamaTyp: TextView
        var Deskripsi: TextView
        var Harga: TextView

        init {
            NamaTyp = view.findViewById(R.id.namatyp)
            Deskripsi = view.findViewById(R.id.deskripsi)
            Harga = view.findViewById(R.id.harga)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_typ, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: TipeAdapter.ViewHolder, position: Int) {
        holder.NamaTyp.text = list[position].NamaTyp
        holder.Deskripsi.text = list[position].Deskripsi
        holder.Harga.text = list[position].Harga
    }
}
