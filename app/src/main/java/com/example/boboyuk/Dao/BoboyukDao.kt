package com.example.boboyuk.Dao

import com.example.boboyuk.Entity.Kamar
import com.example.boboyuk.Entity.Tipe
import com.example.boboyuk.Entity.User
import androidx.room.*

@Dao
interface BoboyukDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun tambahUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM User WHERE uid = :uid")
    fun ambilSemuaUser(uid: Int): User

    @Query("SELECT * FROM user")
    fun getAll(): List<User>


//kamar
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun tambahKamar(kamar: Kamar)

    @Update
    fun updateKamar(kamar: Kamar)

    @Delete
    fun deleteKamar(kamar: Kamar)

    @Query("SELECT * FROM Kamar WHERE kid = :kid")
    fun ambilSemuaKamar(kid: Int): Kamar

    @Query("SELECT * FROM Kamar")
    fun getKmr(): List<Kamar>

    //tipekamar
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun tambahTipe(tipe: Tipe)

    @Update
    fun updateTipe(tipe: Tipe)

    @Delete
    fun deleteTipe(tipe: Tipe)

    @Query("SELECT * FROM Tipe WHERE tid = :tid")
    fun ambilSemuaTipe(tid: Int): Tipe

    @Query("SELECT * FROM Tipe ")
    fun getTyp(): List<Tipe>


}