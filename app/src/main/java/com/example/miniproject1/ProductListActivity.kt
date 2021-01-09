package com.example.miniproject1

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miniproject1.databinding.ActivityEditBinding
import com.example.miniproject1.databinding.ActivityOptionsBinding
import com.example.miniproject1.databinding.ActivityProductListBinding
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding// = ActivityProductListBinding.inflate(layoutInflater)
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductListBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.tvNazwaListy2.text = intent.getStringExtra("nazwa_zmiana")
        sharedPreferences = getSharedPreferences("nazwa_zmiana", Context.MODE_PRIVATE)

//        val viewModel = ProduktViewModel(application)
//        val adapter = MyAdapter(viewModel)
//
//        viewModel.allProducts.observe(this, Observer {
//            it.let {
//                adapter.setProductList(it)
//            }
//        })

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("Produkt")

        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        val list = arrayListOf<String>()
        binding.rvList.adapter = MyAdapter(this, list, ref)

//        binding.btDodusu.setOnClickListener {
//
//            val produktAdd : Produkt = Produkt(
//                    nazwa = binding.etNazwaprod.text.toString(),
//                    cena = binding.etCena.text.toString(),
//                    ilosc = binding.etIlosc.text.toString(),
//                    kupuony = binding.cbKupiony2.isChecked
//            )
//
////            val id = viewModel.addProduct(produktAdd)
//
//            val broadcast = Intent(getString(R.string.addProduct))
//            broadcast.component = ComponentName("com.example.miniproject2", "com.example.miniproject2.AddProductReceiver")
//
////            broadcast.putExtra("idProd", id)
//            broadcast.putExtra("nazwaProd", produktAdd.nazwa)
//            broadcast.putExtra("cenaProd", produktAdd.cena)
//            broadcast.putExtra("iloscProd", produktAdd.ilosc)
//
//            sendBroadcast(broadcast)
//        }

        binding.btDodusu.setOnClickListener {

            val produkt = Produkt (
                nazwa = binding.etNazwaprod.text.toString(),
                cena = binding.etCena.text.toString(),
                ilosc = binding.etIlosc.text.toString(),
                kupuony = binding.cbKupiony2.isChecked
            )

            CoroutineScope(Dispatchers.IO).launch {
                ref.push().setValue(produkt)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tvNazwaListy2.setTextColor(intent.getIntExtra("kolor_textu", Color.GREEN))
        sharedPreferences = getSharedPreferences("nazwa_zmiana", Context.MODE_PRIVATE)

    }

    override fun onStart() {
        super.onStart()
        binding.tvNazwaListy2.text =
            sharedPreferences.getString("NazwaListyZmiana", "Lista nie zostala nazwana")
    }

    override fun onStop() {
        super.onStop()

        val editor = sharedPreferences.edit()
        editor.putString("NazwaListyZmiana", binding.tvNazwaListy2.text.toString())
        editor.apply()
    }
}