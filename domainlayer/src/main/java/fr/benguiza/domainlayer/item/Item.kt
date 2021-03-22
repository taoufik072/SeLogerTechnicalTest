package fr.benguiza.domainlayer.item


data class Item(
    val id: Int,
    val bedrooms: Int?,
    val city: String?,
    val area: Double?,
    val image: String?,
    val price: Double?,
    val professional: String?,
    val propertyType: String?,
    val rooms: Int?
)