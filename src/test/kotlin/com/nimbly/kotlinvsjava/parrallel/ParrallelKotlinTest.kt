package com.nimbly.kotlinvsjava.parrallel

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import java.util.Collections
import kotlin.test.assertEquals

class ParrallelKotlinTest {

    @Test
    fun testJ() {

        val billingService = BillingService()

        runBlocking() {
            launch {
                InvoiceDB.load("Apple").forEach {
                    billingService += it
                }
            }
            launch {
                InvoiceDB.load("Samsung").forEach {
                    billingService += it
                }
            }
        }

        assertEquals(billingService.lines.size, 5)
        assertEquals(billingService.total, 2930.0)
    }
}

fun <A> List<A>.inParallel(lambda: (A) -> Unit) {
    val list = this;
    runBlocking {
        val deferreds = list.map { item: A ->
            async(Dispatchers.IO) {
                lambda(item)
            }
        }
        deferreds.awaitAll()
    }
}

object InvoiceDB {

    fun load(customer: String)
        = all.filter { it.customer == customer }

    val all = listOf(
        Invoice("Apple", "iPhone 14", 850.0),
        Invoice("Apple", "Watch 7", 450.0),
        Invoice("Apple", "AirPods Pro", 350.0),
        Invoice("Samsung", "Galaxy S22", 950.0),
        Invoice("Samsung", "Galaxy Watch 5 Pro", 330.0)
    )
}

class BillingService {

    var total: Double = 0.0
    val lines = Collections.synchronizedList(mutableListOf<Invoice>())

    operator fun plusAssign(invoice: Invoice) {
        lines += invoice
        total += invoice.amount
        println("New invoice ${invoice.label}")
    }

}

class Invoice(
    val customer: String,
    val label: String,
    val amount: Double
)
