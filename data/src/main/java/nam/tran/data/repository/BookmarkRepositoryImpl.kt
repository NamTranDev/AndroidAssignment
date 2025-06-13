package nam.tran.data.repository

import kotlinx.coroutines.flow.map
import nam.tran.data.local.db.ProductDao
import nam.tran.data.local.model.ProductDb
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.repository.BookmarkRepository
import nam.tran.utils.Logger

class BookmarkRepositoryImpl(
    private val productDb: ProductDao
) : BookmarkRepository {

    override val bookmarkedProducts = productDb.getAllProducts().map { productDbs ->
        productDbs.map {
            ProductEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                brand = it.brand,
                category = it.category,
                price = it.price,
                thumbnail = it.thumbnail
            )
        }
    }

    override suspend fun toggleBookmark(productResponse: ProductEntity?) {
        Logger.debug(productResponse)
        productResponse ?: return
        val entity = ProductDb(
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
            productDb.delete(
                entity
            )
        } else {
            productDb.insert(
                entity
            )
        }
    }
}