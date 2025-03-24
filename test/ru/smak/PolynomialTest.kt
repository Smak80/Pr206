package ru.smak

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PolynomialTest {

    @Test
    fun getCoeff() {
        with(Polynomial()) {
            assertEquals(mapOf(0 to 0.0), coeff)
        }
        with(Polynomial(mapOf(1 to 2.5, 2 to 0.0, 3 to 5.2, 0 to 0.0))) {
            assertEquals(mapOf(1 to 2.5, 3 to 5.2), coeff)
        }
    }

    @Test
    fun testToString() {
        with(Polynomial()) {
            assertEquals("0.0", toString())
        }
        with(Polynomial(mapOf(0 to 1.0))) {
            assertEquals("1.0", toString())
        }
        with(Polynomial(mapOf(0 to -1.0))) {
            assertEquals("-1.0", toString())
        }
        with(Polynomial(mapOf(0 to 0.0, 1 to -1.0))) {
            assertEquals("-x", toString())
        }
        with(Polynomial(mapOf(0 to 0.0, 1 to 1.0))) {
            assertEquals("x", toString())
        }
        with(Polynomial(mapOf(0 to 2.0, 1 to 1.0))) {
            assertEquals("x+2.0", toString())
        }
        with(Polynomial(mapOf(2 to -1.0, 1 to 1.0))) {
            assertEquals("-x^2+x", toString())
        }
        with(Polynomial(mapOf(0 to 0.0, -1 to 1.0))) {
            assertEquals("0.0", toString())
        }
    }

    @Test
    fun plus() {
        with(Polynomial(mapOf(3 to 3.0, 0 to -2.0))) {
            val p1 = Polynomial(mapOf(2 to 1.0, 1 to 2.0, 0 to -3.0))
            val p2 = Polynomial(mapOf(3 to 3.0, 2 to -1.0, 1 to -2.0, 0 to 1.0))
            assertEquals(this, p1 + p2)
        }
    }

    @Test
    fun minus() {
    }

    @Test
    fun times() {
        with(Polynomial(mapOf(2 to 6.0, 1 to -7.0, 0 to -3.0))) {
            val p1 = Polynomial(mapOf(1 to 3.0, 0 to 1.0))
            val p2 = Polynomial(mapOf(1 to 2.0, 0 to -3.0,))
            assertEquals(this, p1 * p2)

        }
        assertEquals(Polynomial(mapOf(1 to -3.0, 0 to 9.3)),
            Polynomial(mapOf(1 to -1.0, 0 to 3.1)) * 3.0)

        assertEquals(Polynomial(mapOf(1 to -3.0, 0 to 9.3)),
            3.0 * Polynomial(mapOf(1 to -1.0, 0 to 3.1)))
    }
}