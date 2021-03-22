package fr.benguiza.selogertest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import fr.benguiza.domainlayer.item.GetItemsUseCase
import fr.benguiza.domainlayer.item.RetrieveItemsUseCase
import fr.benguiza.domainlayer.item.SaveToDataBaseItemsUseCase
import fr.benguiza.selogertest.R
import fr.benguiza.selogertest.test.BaseTest
import fr.benguiza.selogertest.test.ItemUiData
import fr.benguiza.selogertest.test.TestFragmentRule
import fr.benguiza.selogertest.tools.extensions.AppBuildConfig
import fr.benguiza.selogertest.ui.item.listing.ItemsListingFragment
import fr.benguiza.selogertest.ui.item.listing.ItemsListingViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.reactivex.Observable
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions.Companion.childOfViewAtPositionWithMatcher
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ItemDetailsFragmentTest : BaseTest() {

    private var retrieveItemsUseCase: RetrieveItemsUseCase = mockk()
    private var saveToDataBase: SaveToDataBaseItemsUseCase = mockk()
    private var getItemsUseCase: GetItemsUseCase = mockk()
    private lateinit var data: ItemUiData

    @Rule
    @JvmField
    var fragmentRule = TestFragmentRule.create()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        data = ItemUiData()

        every { retrieveItemsUseCase.execute() } returns Observable.just(data.domain.items)
        every { getItemsUseCase.execute() } returns Observable.just(data.domain.items)
        mockkObject(AppBuildConfig)
        every { AppBuildConfig.getTestingValue() } returns true

    }

    private fun showFragment() {
        with(ItemsListingFragment()) {
            viewModel = ItemsListingViewModel(retrieveItemsUseCase, saveToDataBase, getItemsUseCase)
            fragmentRule.setFragment(this)
        }
    }

    @Test
    fun showItemDetails() {
        // When
        showFragment()

        // Then
        onView(withId(R.id.view_items_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    allOf(
                        childOfViewAtPositionWithMatcher(
                            R.id.view_item_city,
                            0,
                            withText("A")
                        ),
                        childOfViewAtPositionWithMatcher(
                            R.id.view_item_price,
                            0,
                            withText("123,00")
                        )
                    )
                )
            )

    }


}