package fr.benguiza.datalayer.items

import com.google.gson.annotations.SerializedName

data class ItemsJson(
    @SerializedName("items") val items: List<ItemJson>,
    @SerializedName("totalCount") val count: Int
)

data class ItemJson(
    @SerializedName("id") val id: Int,
    @SerializedName("bedrooms") val bedrooms: Int?,
    @SerializedName("city") val city: String?,
    @SerializedName("area") val area: Double?,
    @SerializedName("url") val image: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("professional") val professional: String?,
    @SerializedName("propertyType") val propertyType: String?,
    @SerializedName("rooms") val rooms: Int?
)