package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CalculadoraActivity : AppCompatActivity() {
    
    private lateinit var txtNombre: TextView
    private lateinit var txtNum1: EditText
    private lateinit var txtNum2: EditText
    private lateinit var spOperaciones: Spinner
    private lateinit var txtResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button
    
    private var operacionSeleccionada: Int = 0 // 0: Suma, 1: Resta, 2: Multiplicación, 3: División
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)
        

        val nombre = intent.getStringExtra("nombre") ?: ""
        

        txtNombre = findViewById(R.id.txtNombre)
        txtNum1 = findViewById(R.id.txtNum1)
        txtNum2 = findViewById(R.id.txtNum2)
        spOperaciones = findViewById(R.id.spOperaciones)
        txtResultado = findViewById(R.id.txtResultado)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)
        

        txtNombre.text = nombre
        

        val operaciones = resources.getStringArray(R.array.operaciones_array)
        val adapter = ArrayAdapter(this, R.layout.spinner_item, operaciones)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spOperaciones.adapter = adapter
        

        spOperaciones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                operacionSeleccionada = position
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {
                operacionSeleccionada = 0
            }
        }
        

        btnCalcular.setOnClickListener {
            calcular()
        }
        

        btnLimpiar.setOnClickListener {
            limpiar()
        }
        

        btnRegresar.setOnClickListener {
            finish()
        }
    }
    
    private fun calcular() {
        // Validación: todos los datos son requeridos
        val num1Str = txtNum1.text.toString().trim()
        val num2Str = txtNum2.text.toString().trim()
        
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, getString(R.string.datos_requeridos), Toast.LENGTH_SHORT).show()
            return
        }
        

        val num1: Float
        val num2: Float
        
        try {
            num1 = num1Str.toFloat()
            num2 = num2Str.toFloat()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Por favor ingrese solo números válidos", Toast.LENGTH_SHORT).show()
            return
        }
        

        val operaciones = Operaciones(num1, num2)
        

        val resultado: Float = when (operacionSeleccionada) {
            0 -> operaciones.suma()
            1 -> operaciones.resta()
            2 -> operaciones.mult()
            3 -> {
                try {
                    operaciones.divicion()
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(this, getString(R.string.error_division_cero), Toast.LENGTH_SHORT).show()
                    txtResultado.text = ""
                    return
                }
            }
            else -> 0f
        }
        

        txtResultado.text = resultado.toString()
    }
    
    private fun limpiar() {
        txtNum1.text.clear()
        txtNum2.text.clear()
        txtResultado.text = ""
        spOperaciones.setSelection(0)
    }
}
