package fr.benguiza.selogertest.ui.item

import fr.benguiza.domainlayer.item.Item


internal fun Item.toUi() =
    ItemUi(
        id = id,
        rooms = rooms ?: 0,
        propertyType = propertyType ?: "",
        professional = professional ?: "",
        price = price ?: 0.0,
        image = image ?: "",
        city = city ?: "",
        bedrooms = bedrooms ?: 0,
        area = area ?: 0.0

    )

