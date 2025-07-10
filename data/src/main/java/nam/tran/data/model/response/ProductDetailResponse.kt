package nam.tran.data.model.response

import nam.tran.domain.model.entity.ProductDetailEntity

data class ProductDetailResponse(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val category: String? = null,
    val price: Double? = null,
    val discountPercentage: Double? = null,
    val rating: Double? = null,
    val tags: List<String>? = null,
    val brand: String? = null,
    val sku: String? = null,
    val weight: Int? = null,
    val warrantyInformation: String? = null,
    val shippingInformation: String? = null,
    val availabilityStatus: String? = null,
    val thumbnail: String? = null,
) {
    fun toProduct(): ProductResponse? {
        return ProductResponse(
            id = id,
            title = title,
            description = description,
            brand = brand,
            category = category,
            price = price,
            thumbnail = thumbnail
        )
    }

    fun toProductDetailEntity(): ProductDetailEntity {
        return ProductDetailEntity(
            id = id,
            title = title,
            description = description,
            category = category,
            price = price,
            discountPercentage = discountPercentage,
            rating = rating,
            tags = tags,
            brand = brand,
            sku = sku,
            weight = weight,
            warrantyInformation = warrantyInformation,
            shippingInformation = shippingInformation,
            availabilityStatus = availabilityStatus,
            thumbnail = thumbnail
        )
    }
}