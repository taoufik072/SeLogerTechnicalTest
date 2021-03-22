package fr.benguiza.selogertest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import fr.benguiza.domainlayer.item.GetItemByIdUseCase
import fr.benguiza.domainlayer.item.GetLocalItemByIdUseCase
import fr.benguiza.selogertest.R
import fr.benguiza.selogertest.test.BaseTest
import fr.benguiza.selogertest.test.ItemUiData
import fr.benguiza.selogertest.test.TestFragmentRule
import fr.benguiza.selogertest.tools.extensions.AppBuildConfig
import fr.benguiza.selogertest.tools.extensions.bundle
import fr.benguiza.selogertest.ui.item.details.ARG_ITEM_ID
import fr.benguiza.selogertest.ui.item.details.ItemDetailsFragment
import fr.benguiza.selogertest.ui.item.details.ItemsDetailsViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListItemsFragmentTest : BaseTest() {

    private var getItemByIdUseCase: GetItemByIdUseCase = mockk()
    private var getLocalItemByIdUseCase: GetLocalItemByIdUseCase = mockk()
    private lateinit var data: ItemUiData
    private lateinit var viewModele: ItemsDetailsViewModel

    @Rule
    @JvmField
    var fragmentRule = TestFragmentRule.create()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        data = ItemUiData()
        viewModele = ItemsDetailsViewModel(getItemByIdUseCase, getLocalItemByIdUseCase)
        every { getItemByIdUseCase.execute(1) } returns Observable.just(data.domain.items.first())
        every { getLocalItemByIdUseCase.execute(1) } returns Observable.just(data.domain.items.first())
        mockkObject(AppBuildConfig)
        every { AppBuildConfig.getTestingValue() } returns true
    }

    private fun showFragment() {
        with(ItemDetailsFragment()) {
            viewModel = viewModele
            arguments = bundle {
                putInt(
                    ARG_ITEM_ID,
                    1
                )
            }
            fragmentRule.setFragment(this)
        }
    }


    @Test
    fun showItemDetails() {
        // When
        showFragment()

        // Then
        assertDisplayed(R.id.view_item_property_rooms, "3 piéces")
        assertDisplayed(R.id.view_item_property_bedrooms, "3 chambres")
        assertDisplayed(R.id.city_text, "A")
        assertDisplayed(R.id.view_item_professional, "professional")
        assertDisplayed(R.id.view_item_property_type, "Type de propriété : propertyType")

    }


}