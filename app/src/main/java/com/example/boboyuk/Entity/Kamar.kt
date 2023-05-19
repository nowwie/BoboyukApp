package com.example.boboyuk.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Kamar (
    @PrimaryKey(autoGenerate = true) var kid: Int? =null,
    @ColumnInfo(name = "Kamar") var NameKamar: String?,
    @ColumnInfo(name = "idTyp") var idTyp: String?

)