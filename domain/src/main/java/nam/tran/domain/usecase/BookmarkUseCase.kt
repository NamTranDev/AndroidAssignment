package nam.tran.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.repository.BookmarkRepository

interface LoadBookmarkProductUseCase : FlowUseCase<Unit,List<ProductEntity>>
interface ToggleBookmarkProductUseCase : UseCase<ProductEntity?,Unit>

class LoadBookmarkProductUseCaseImpl(
    private val bookmarkRepository: BookmarkRepository
) : LoadBookmarkProductUseCase{
    override fun execute(params: Unit): Flow<List<ProductEntity>> {
        return bookmarkRepository.bookmarkedProducts.map { it ->
            it.map {
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
    }
}

class ToggleBookmarkProductUseCaseImpl(
    private val bookmarkRepository: BookmarkRepository
) : ToggleBookmarkProductUseCase {
    override suspend fun execute(params: ProductEntity?) {
        bookmarkRepository.toggleBookmark(params)
    }

}