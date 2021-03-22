package fr.benguiza.datalayer.items

import fr.benguiza.domainlayer.item.Item


internal fun ItemJson.toItem() = Item(
    id = id,
    area = area,
    bedrooms = bedrooms,
    city = city,
    image = image,
    price = price,
    professional = professional,
    propertyType = propertyType,
    rooms = rooms
)

internal fun Item.toItemEntity() = ItemEntity(
    id = id,
    rooms = rooms,
    propertyType = propertyType,
    professional = professional,
    price = price,
    image = image,
    city = city,
    bedrooms = bedrooms,
    area = area
)

internal fun ItemEntity.toItem() = Item(
    id = id,
    area = area,
    bedrooms = bedrooms,
    city = city,
    image = image,
    price = price,
    professional = professional,
    propertyType = propertyType,
    rooms = rooms
)