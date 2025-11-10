package com.example.calculadora

class Operaciones(private val num1: Float, private val num2: Float) {
    
    fun suma(): Float {
        return num1 + num2
    }
    
    fun resta(): Float {
        return num1 - num2
    }
    
    fun mult(): Float {
        return num1 * num2
    }
    
    fun divicion(): Float {
        if (num2 == 0f) {
            throw IllegalArgumentException("No se puede dividir por cero")
        }
        return num1 / num2
    }
}

