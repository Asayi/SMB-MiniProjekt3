package com.example.miniproject1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Produkt(
//        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var nazwa: String,
        var cena: String,
        var ilosc: String,
        var kupuony: Boolean
    )