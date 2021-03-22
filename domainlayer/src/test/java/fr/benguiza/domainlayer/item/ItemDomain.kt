package fr.benguiza.domainlayer.item

class ItemDomain {

    private val item1 = Item(
        id = 1,
        city = "A",
        area = 255.0,
        image = "image1",
        bedrooms = 3,
        price = 123.0,
        professional = "professional",
        propertyType = "propertyType",
        rooms = 3
    )

    private val item2 = Item(
        id = 2,
        city = "B",
        area = 255.0,
        image = "image1",
        bedrooms = 3,
        price = 123.0,
        professional = "professional",
        propertyType = "propertyType",
        rooms = 3
    )

    val items = listOf(item1, item2)

}