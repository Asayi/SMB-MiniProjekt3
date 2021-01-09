package com.example.miniproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.miniproject1.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = FirebaseAuth.getInstance()

        binding.btLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding.etLogin.text.toString(),
                binding.etHaslo.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Zalogowano", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java)
                            .also {
                                it.putExtra("user", auth.currentUser?.email)
                            })
                    } else {
                        Toast.makeText(this, "Blad logowania", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.btZarejestruj.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.etLogin.text.toString(),
                binding.etHaslo.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Zarejestrowano", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Blad rejestracji", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}