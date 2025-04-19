package nam.tran.home.assignment.jetpack.compose.data.repository

import io.lifestyle.plus.utils.Logger
import nam.tran.home.assignment.jetpack.compose.data.local.db.ProductDao
import nam.tran.home.assignment.jetpack.compose.data.local.model.ProductEntity
import nam.tran.home.assignment.jetpack.compose.domain.repository.BookmarkRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

class BookmarkRepositoryImpl(
    private val productDb: ProductDao
) : BookmarkRepository {

    override val bookmarkedProducts = productDb.getAllProducts()

    override suspend fun toggleBookmark(productResponse: ProductResponse?) {
        Logger.debug(productResponse)
        productResponse ?: return
        val entity = ProductEntity(
            id = productResponse.id,
            title = productResponse.title,
            description = productResponse.description,
            brand = productResponse.brand,
            category = productResponse.category,
            price = productResponse.price,
            thumbnail = productResponse.thumbnail
        )
        val isBookmarked = productDb.isBookmarked(productResponse.id)
        if (isBookmarked) {
            productDb.delete(entity)
        } else {
            productDb.insert(entity)
        }
    }
}