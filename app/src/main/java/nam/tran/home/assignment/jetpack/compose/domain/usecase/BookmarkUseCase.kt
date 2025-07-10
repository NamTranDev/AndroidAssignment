package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nam.tran.home.assignment.jetpack.compose.data.local.model.ProductEntity
import nam.tran.home.assignment.jetpack.compose.domain.repository.BookmarkRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

interface LoadBookmarkProductUseCase : FlowUseCase<Unit,List<ProductResponse>>
interface ToggleBookmarkProductUseCase : UseCase<ProductResponse?,Unit>

class LoadBookmarkProductUseCaseImpl(
    private val bookmarkRepository: BookmarkRepository
) : LoadBookmarkProductUseCase{
    override fun execute(params: Unit): Flow<List<ProductResponse>> {
        return bookmarkRepository.bookmarkedProducts.map { it ->
            it.map {
                ProductResponse(
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
    override suspend fun execute(params: ProductResponse?) {
        bookmarkRepository.toggleBookmark(params)
    }

}