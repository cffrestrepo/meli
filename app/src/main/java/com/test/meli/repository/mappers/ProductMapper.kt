package com.test.meli.repository.mappers

import com.test.meli.data.local.entities.ProductEntity
import com.test.meli.data.remote.response.Results
import com.test.meli.repository.models.ResultsModel

object ProductMapper {

    fun productResponseToProductEntity(products: List<Results>): List<ProductEntity> =
        products.map {
            ProductEntity(
                title = it.title,
                thumbnail = it.thumbnail,
                price = it.price,
                nickname = it.seller?.nickname ?: "",
                address = it.price,
                city = it.address?.city_name ?: ""
            )
        }

    fun productEntityToProductModel(products: List<ProductEntity>): List<ResultsModel> =
        products.map {
            ResultsModel(
                title = it.title,
                thumbnail = it.thumbnail,
                price = it.price,
                nickname = it.nickname,
                address = it.address,
                city_name = it.city
            )
        }
}