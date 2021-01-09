package com.example.miniproject1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.style.BackgroundColorSpan
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miniproject1.databinding.ActivityOptionsBinding
import com.example.miniproject1.databinding.ActivityProductListBinding
import kotlin.properties.Delegates
import kotlin.random.Random

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionsBinding
    private lateinit var colorsArray: IntArray
    private var randomVale by Delegates.notNull<Int>()
    private lateinit var sharedPreferences: SharedPreferences
    private var fontSize = 24f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("nazwa_zmiana", Context.MODE_PRIVATE)

        //?? nowe //kolor textu
        colorsArray = intArrayOf(Color.CYAN, Color.MAGENTA, Color.GREEN, Color.LTGRAY, Color.RED)
        randomVale = Random.nextInt(colorsArray.size)
//        sharedPreferences = getSharedPreferences("kolor_textu", Context.MODE_PRIVATE)

        //przyciski
        binding.bt1NazwaListy.setOnClickListener {
            binding.tvNazwaListy3.text = binding.etNazwaListy.text.toString()
            val nazwaListy = binding.tvNazwaListy3.text
            intent.putExtra("nazwa_zmiana", nazwaListy)

            Toast.makeText(this, "Nazwa listy zostala zmieniona", Toast.LENGTH_LONG).show()
        }

        binding.bt2KolorNazwyListy.setOnClickListener {
            randomVale = Random.nextInt(colorsArray.size)
            binding.tvNazwaListy3.setTextColor(colorsArray[randomVale])

            val colorValue: Int = colorsArray[randomVale]

            intent.putExtra("kolor_textu", colorValue)
            Toast.makeText(this, "Kolor zostal zmieniony", Toast.LENGTH_LONG).show()
        }

        binding.btRozmiarCzcionkiZw.setOnClickListener {
            fontSize += 4f
            binding.tvNazwaListy3.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize)
            Toast.makeText(this, "Rozmiar tekstu zostal zwiekszony", Toast.LENGTH_LONG).show()
            intent.putExtra("rozmiar_tekstu+", fontSize)
        }

        binding.btRozmiarCzcionkiZm.setOnClickListener {
            fontSize -= 4f
            binding.tvNazwaListy3.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize)
            Toast.makeText(this, "Rozmiar tekstu zostal zmniejszony", Toast.LENGTH_LONG).show()
            intent.putExtra("rozmiar_tekstu-", fontSize)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.tvNazwaListy3.text = sharedPreferences.getString("NazwaListyZmiana", "Lista nie zostala nazwana")
        //?? nowe
        intent.getIntExtra("kolor_textu", Color.BLACK)
        binding.tvNazwaListy3.setTextColor(intent.getIntExtra("kolor_textu", colorsArray[randomVale]))//colorsArray[randomVale])
//        sharedPreferences.getInt("KolorTextuZmiana", colorsArray[randomVale])
    }

    override fun onStop() {
        super.onStop()
        val editor = sharedPreferences.edit()
        editor.putString("NazwaListyZmiana", binding.tvNazwaListy3.text.toString())
        //?? nowe
        editor.putInt("kolor_textu", intent.getIntExtra("kolor_textu", Color.GREEN))
        editor.apply()
    }
}