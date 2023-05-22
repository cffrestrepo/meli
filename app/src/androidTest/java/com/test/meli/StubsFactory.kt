package com.test.meli

import com.test.meli.data.remote.response.Address
import com.test.meli.data.remote.response.LookFor
import com.test.meli.data.remote.response.Results
import com.test.meli.data.remote.response.Seller

const val PERRO = "Perro"

fun createLooFor() = LookFor(
    query = PERRO,
    results = listOf(createResults(), createResults())
)

fun createResults() = Results(
    id = "12345",
    title = PERRO,
    thumbnail = "https://bankimages.com/imagen.png",
    price = "5000",
    seller = createSeller(),
    address = createAddress()
)

fun createSeller() = Seller(
    nickname = "Mercado libre"
)

fun createAddress() = Address(
    address = "cll 4 # 28 - 141",
    city_name = "Zipaquira"
)