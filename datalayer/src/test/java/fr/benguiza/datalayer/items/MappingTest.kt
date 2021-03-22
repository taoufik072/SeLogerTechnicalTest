package fr.benguiza.datalayer.items

import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test

class MappingTest {

    private lateinit var data: ItemData

    @Before
    fun setUp(){
        data = ItemData()
    }

    @Test
    fun mapToDomain() {
        // Given
        val expected = data.domain.items
        // When
        val domain = data.json.items.items.map {  it.toItem() }
        // Then
        domain `should be equal to` expected
    }
}