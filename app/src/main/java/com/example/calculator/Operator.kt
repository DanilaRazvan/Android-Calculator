package com.example.calculator

sealed class Operator(val x: Double) {
    abstract fun calculate(y: Double): Double

    class Add(x: Double): Operator(x) {
        override fun calculate(y: Double): Double {
            return x + y
        }
    }

    class Sub(x: Double): Operator(x) {
        override fun calculate(y: Double): Double {
            return x - y
        }
    }

    class Mult(x: Double): Operator(x) {
        override fun calculate(y: Double): Double {
            return x * y
        }
    }

    class Div(x: Double): Operator(x) {
        override fun calculate(y: Double): Double {
            return x / y
        }
    }


}