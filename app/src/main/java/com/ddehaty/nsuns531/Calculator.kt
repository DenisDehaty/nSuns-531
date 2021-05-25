package com.ddehaty.nsuns531

import kotlin.math.ceil

class Calculator {



    companion object {
        private fun ceilExcel(number: Double, significance: Double): Double {
            var quotient = number / significance
            if (quotient % 1 != 0.0) {
                quotient = ceil(quotient)
                return quotient * significance
            }
            return number
        }

        fun calculateWeight(weight: Double, percentage: Double): Double {
            var trainingMax = (weight * percentage)
            trainingMax = ceilExcel(trainingMax, 2.5)
            return trainingMax
        }
    }


}