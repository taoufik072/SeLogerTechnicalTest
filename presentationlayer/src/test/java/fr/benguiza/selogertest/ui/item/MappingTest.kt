package fr.benguiza.selogertest.ui.item

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class MappingTest {

    private lateinit var data: ItemUiData

    @Before
    fun setUp() {
        data = ItemUiData()
    }

    @Test
    fun mapDomainToUi() {
        //Given
        val expected = data.ui.items
        //when
        val ui = data.domain.items.map { it.toUi() }

        ui shouldBeEqualTo expected
    }
}