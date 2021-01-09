//package com.example.miniproject1
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [Produkt::class], version = 1)
//abstract class ProduktDB : RoomDatabase(){
//    abstract fun getProduktDao(): ProduktDao
//
//    companion object {
//        private var instance : ProduktDB? = null
//
//        fun getDatabase(context: Context): ProduktDB {
//            if(instance != null)
//                return instance as ProduktDB
//            instance = Room.databaseBuilder(
//                context,
//                ProduktDB::class.java,
//                "produktDB"
//            ).allowMainThreadQueries().build()
//            return instance as ProduktDB
//        }
//    }
//}