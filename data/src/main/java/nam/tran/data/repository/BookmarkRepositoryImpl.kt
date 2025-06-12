package nam.tran.data.repository

import nam.tran.data.local.db.ProductDao
import nam.tran.data.model.response.ProductResponse
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.repository.BookmarkRepository
import java.util.logging.Logger

class BookmarkRepositoryImpl(
    private val productDb: ProductDao
) : BookmarkRepository {

    override val bookmarkedProducts = productDb.getAllProducts()

    override suspend fun toggleBookmark(productResponse: ProductEntity?) {
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