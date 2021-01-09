package com.example.miniproject1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.miniproject1.databinding.ActivityEditBinding

internal class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val viewModel = ProduktViewModel(application)
//        val adapter = MyAdapter(viewModel)
//
//        val id = intent.getLongExtra("idprod", 0)
//        binding.tvPassedId.text = id.toString()
//
//        val prodname = intent.getStringExtra("prodname")
//        binding.tvEditprodname.text = prodname.toString()
//
//        viewModel.allProducts.observe(this, Observer {
//            it.let {
//                adapter.setProductList(it)
//            }
//        })
//
//        binding.btZapiszPoEd.setOnClickListener {
//            if (id >= 0) {
//                viewModel.modifyProduct(
//                        Produkt(
//                                id = id,
//                                nazwa = binding.etNazwaProdEd.text.toString(),
//                                cena = binding.etCenaEd.text.toString(),
//                                ilosc = binding.etIloscEd.text.toString(),
//                                kupuony = false
//                        )
//                )
//                adapter.notifyDataSetChanged()
//                Toast.makeText(this, "Rekord zostal zmodyfikowany", Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(this, "Wrong ID number", Toast.LENGTH_LONG).show()
//            }
//        }
    }
}