//package com.example.miniproject1
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//
//class ProduktViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val repo: ProduktRepo
//    val allProducts: LiveData<List<Produkt>>
//
//    init {
//        repo = ProduktRepo(ProduktDB.getDatabase(application.applicationContext).getProduktDao())
//        allProducts = repo.getProducts()
//    }
//
//    fun getProducts() = repo.getProducts()
//    fun addProduct(produkt: Produkt) : Long = repo.addProduct(produkt)
//    fun modifyProduct(produkt: Produkt) = repo.modifyProduct(produkt)
//    fun removeProduct(produkt: Produkt) = repo.removeProduct(produkt)
//    fun removeAllProducts() = repo.removeAllProducts()
//
//
//}