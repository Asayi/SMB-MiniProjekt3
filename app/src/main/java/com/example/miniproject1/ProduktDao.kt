//package com.example.miniproject1
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//
//@Dao
//interface ProduktDao {
//
//    @Query("select * from produkt")
//    fun getProducts(): LiveData<List<Produkt>>
//
//    @Insert
//    fun addProduct(produkt: Produkt) : Long
//
//    @Update
////    @Query("update Produkt set nazwa = Produkt.nazwa, cena = Produkt.cena, ilosc = Produkt.ilosc where id = Produkt.id")
//    fun modifyProduct(produkt: Produkt)
//
//    @Delete
//    fun removeProduct(produkt: Produkt)
//
//    @Query("delete from produkt")
//    fun removeAllProducts()
//}