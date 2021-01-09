package com.example.miniproject1

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniproject1.databinding.ListElementBinding
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyAdapter(val context: Context, val list: ArrayList<Produkt>, val ref: DatabaseReference) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    init {
        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                CoroutineScope(IO).launch {
                    list.add(snapshot.value as Produkt)
                    withContext(Main) {
                        notifyDataSetChanged()
                    }
                }
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                CoroutineScope(IO).launch {
                    list.remove(snapshot.value as String)
                    list.add(snapshot.value as Produkt)
                    withContext(Main) {
                        notifyDataSetChanged()
                    }
                }
            }
            override fun onChildRemoved(snapshot: DataSnapshot) {
                CoroutineScope(IO).launch {
                    list.remove(snapshot.value as String)
                    withContext(Main) {
                        notifyDataSetChanged()
                    }
                }
            }
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    class MyViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvNazwa.text = list[position].nazwa
        holder.binding.tvCena.text = list[position].cena
        holder.binding.tvIlosc.text = list[position].ilosc
        holder.binding.cbKupione.isChecked = false

        holder.binding.tvNazwa.setOnClickListener {
            ref.orderByValue().equalTo(list[position].nazwa).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    CoroutineScope(IO).launch {
                        snapshot.children.iterator().next().ref.removeValue()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("MyAdapter", "Failed to delete value", error.toException())
                }
            })
        }
    }
    override fun getItemCount(): Int = list.size
}






















//
//class MyAdapter(val viewModel: ProduktViewModel) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
//
//    private var productList = emptyList<Produkt>()
//    private lateinit var binding: ListElementBinding
//
//    class MyViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        binding = ListElementBinding.inflate(inflater, parent, false)
//        return MyViewHolder(binding)
//    }
//
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
//        holder.binding.tvId.text = productList[position].id.toString()
//        holder.binding.tvNazwa.text = productList[position].nazwa
//        holder.binding.tvCena.text = productList[position].cena
//        holder.binding.tvIlosc.text = productList[position].ilosc
//        holder.binding.cbKupione.isChecked = productList[position].kupuony
//
//        holder.binding.cbKupione.setOnClickListener {
//            if (holder.binding.cbKupione.isChecked) {
//                viewModel.removeProduct(productList[position])
//                notifyDataSetChanged()
//            }
//        }
//
//        holder.binding.root.setOnClickListener {
//            val productEdit = EditActivity()
//            val editIntent = Intent(holder.binding.root.context, EditActivity::class.java)
//
//            holder.binding.root.context.startActivity(editIntent)
//
//            viewModel.modifyProduct(Produkt(
//                    id = productList[position].id,
//                    nazwa = productEdit.binding.etNazwaProdEd.text.toString(),
//                    cena = productEdit.binding.etCenaEd.text.toString(),
//                    ilosc = productEdit.binding.etIloscEd.text.toString(),
//                    kupuony = false
//            ))
//            notifyDataSetChanged()
//        }
//    }
//
//
//    override fun getItemCount(): Int = productList.size
//
//    fun setProductList(list: List<Produkt>) {
//        productList = list
//        notifyDataSetChanged()
//    }
//}