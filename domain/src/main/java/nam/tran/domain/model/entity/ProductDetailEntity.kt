package nam.tran.domain.model.entity

data class ProductDetailEntity(
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
    fun toProduct(): ProductEntity {
        return ProductEntity(
            id = id,
            title = title,
            description = description,
            brand = brand,
            category = category,
            price = price,
            thumbnail = thumbnail
        )
    }
}