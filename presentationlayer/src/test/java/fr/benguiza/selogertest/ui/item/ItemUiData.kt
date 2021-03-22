package fr.benguiza.selogertest.ui.item

import fr.benguiza.domainlayer.item.Item

class ItemUiData {

    val ui by lazy { Ui() }
    val domain by lazy { Domain() }

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
        val items = listOf(item1, item2)
    }

    class Ui {

        private val item1 = ItemUi(
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

        private val item2 = ItemUi(
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
}