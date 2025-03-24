package ru.smak

import java.util.*
import kotlin.math.absoluteValue

class Polynomial(coeff: Map<Int, Double> = mapOf(0 to 0.0) ) {
    private var _coeff: SortedMap<Int, Double> = coeff.toSortedMap()
    val coeff: Map<Int, Double>
        get() = _coeff.toMap()

    init{
        checkZeroes()
    }

    override fun toString(): String = buildString {
        for ((power, value) in _coeff.reversed()) {
            if (value >= 0.0) {
                if (power < _coeff.lastKey()) append("+")
            } else append("-")

            if (value.absoluteValue != 1.0 || power == 0) {
                append(value.absoluteValue)
            }

            if (power != 0) append('x')
            if (power > 1) append('^').append(power)
        }
    }

    private fun checkZeroes() {
        _coeff = _coeff
            .filterValues { it != 0.0 }
            .filterKeys { it >= 0 }
            .toSortedMap()
            .apply{
            if (isEmpty()) this[0] = 0.0
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Polynomial) return false
        return _coeff.equals(other._coeff)
    }

    override fun hashCode(): Int = _coeff.hashCode() * 31

    operator fun plus(other: Polynomial) = Polynomial(
        _coeff.toMutableMap().apply {
            other._coeff.forEach { p, v -> this[p] = (this[p] ?: 0.0) + v }
        }
    )

    operator fun minus(other: Polynomial) = Polynomial(
        _coeff.toMutableMap().apply {
            other._coeff.forEach { p, v -> this[p] = (this[p] ?: 0.0) - v }
        }
    )

    operator fun times(other: Polynomial) = Polynomial(
        mutableMapOf<Int, Double>().also {
            _coeff.forEach{ p1, v1  ->
                other._coeff.forEach{ p2, v2 ->
                    it[p1 + p2] = (it[p1 + p2] ?: 0.0) + v1 * v2
                }
            }
        }
    )
    operator fun times(k: Double) = Polynomial(
        _coeff.map{ (p, v) -> p to k * v }.toMap()
    )
    operator fun div(k: Double) = Polynomial(
        _coeff.map{ (p, v) -> p to v / k }.toMap()
    )


}

operator fun Double.times(p:Polynomial):Polynomial = p * this