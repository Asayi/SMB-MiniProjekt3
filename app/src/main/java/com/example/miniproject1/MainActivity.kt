package com.example.miniproject1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miniproject1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLoggedAs.text = intent.getStringExtra("user")

        binding.btListaprod.setOnClickListener {
            val intentListaProd = Intent(this, ProductListActivity::class.java)
            startActivity(intentListaProd)
        }

        binding.btOpcje.setOnClickListener {
            val intentOptions = Intent(this, OptionsActivity::class.java)
            startActivity(intentOptions)
        }
    }
}