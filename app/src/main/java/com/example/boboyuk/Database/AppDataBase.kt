package com.example.boboyuk.Database

import com.example.boboyuk.Dao.BoboyukDao
import com.example.boboyuk.Entity.Kamar
import com.example.boboyuk.Entity.Tipe
import com.example.boboyuk.Entity.User
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class, Tipe::class, Kamar::class], version = 3)
abstract class AppDatabase :RoomDatabase(){
    abstract fun BoboyukDao(): BoboyukDao

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            if(instance==null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "Boboyuk")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}
