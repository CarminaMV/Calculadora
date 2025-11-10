package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var txtNombre: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnCerrar: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

        txtNombre = findViewById(R.id.txtNombre)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnCerrar = findViewById(R.id.btnCerrar)
        

        btnIngresar.setOnClickListener {
            val nombre = txtNombre.text.toString().trim()
            

            if (nombre.isEmpty()) {
                Toast.makeText(this, getString(R.string.datos_requeridos), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            

            val intent = Intent(this, CalculadoraActivity::class.java)
            intent.putExtra("nombre", nombre)
            startActivity(intent)
        }
        

        btnCerrar.setOnClickListener {
            finish()
        }
    }
}
