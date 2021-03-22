package fr.benguiza.datalayer.items

import fr.benguiza.domainlayer.item.Item

class ItemData {

    val json by lazy { Json() }
    val domain by lazy { Domain() }

    class Json {


        private val item1 = ItemJson(
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

        private val item2 = ItemJson(
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
        val items = ItemsJson(listOf(item1,item2),2)
    }

    class Domain {

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
        val items = listOf(item1,item2)

    }
}