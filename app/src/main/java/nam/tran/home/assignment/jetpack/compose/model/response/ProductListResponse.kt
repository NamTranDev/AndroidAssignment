package nam.tran.home.assignment.jetpack.compose.model.response

import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    val products : List<ProductResponse>
)