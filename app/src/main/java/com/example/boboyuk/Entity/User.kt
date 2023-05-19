package com.example.boboyuk.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (
    @PrimaryKey(autoGenerate = true) var uid: Int? =null,
    @ColumnInfo(name = "Name") var Name: String?,
    @ColumnInfo(name = "Username") var Username: String?,
    @ColumnInfo(name = "Password") var Password: String?,
    @ColumnInfo(name ="Job") var Job: String?
)