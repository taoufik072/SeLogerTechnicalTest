package fr.benguiza.datalayer.items

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ItemEntity(
    @PrimaryKey var id: Int = 0,
    var bedrooms: Int? = null,
    var city: String? = null,
    var area: Double? = null,
    var image: String? = null,
    var price: Double? = null,
    var professional: String? = null,
    var propertyType: String? = null,
    var rooms: Int? = null

) : RealmObject()