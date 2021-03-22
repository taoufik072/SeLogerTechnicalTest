package fr.benguiza.selogertest.di

import dagger.Subcomponent
import fr.benguiza.selogertest.BaseFragment
import fr.benguiza.selogertest.ui.item.details.ItemDetailsFragment
import fr.benguiza.selogertest.ui.item.listing.ItemsListingFragment

@Subcomponent
interface FragmentsComponent {
    fun inject(fragment: ItemsListingFragment)
    fun inject(fragment: ItemDetailsFragment)
    fun inject(fragment: BaseFragment)
}