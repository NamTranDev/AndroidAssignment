package nam.tran.home.assignment.jetpack.compose.data.repository

import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.data.network.api.ProductApi

class ProductRepositoryImpl(
    private val productApi : ProductApi
): ProductRepository{


}