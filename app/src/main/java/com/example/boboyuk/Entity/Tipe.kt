package com.example.boboyuk.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Tipe (
    @PrimaryKey(autoGenerate = true) var tid: Int? =null,
    @ColumnInfo(name = "NamaTyp") var NamaTyp: String?,
    @ColumnInfo(name = "Deskripsi") var Deskripsi: String?,
//    @ColumnInfo(name = "idTyp") var idTyp: String?,
    @ColumnInfo(name = "Harga") var Harga: String?
)