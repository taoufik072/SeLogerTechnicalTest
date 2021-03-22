package fr.benguiza.selogertest.di.modules

import dagger.Module
import dagger.Provides
import fr.benguiza.domainlayer.item.*
import fr.benguiza.selogertest.ui.item.details.ItemsDetailsViewModel
import fr.benguiza.selogertest.ui.item.details.IItemsDetailsViewModel
import fr.benguiza.selogertest.ui.item.listing.ItemsListingViewModel
import fr.benguiza.selogertest.ui.item.listing.IItemsListingViewModel

@Module
class ViewModelsModule {

    @Provides
    fun provideItemListingViewModel(
        retrieveItemsUseCase: RetrieveItemsUseCase,
        saveToDataBaseItemsUseCase: SaveToDataBaseItemsUseCase,
        getItemsUseCase: GetItemsUseCase
    ): IItemsListingViewModel {
        return ItemsListingViewModel(
            retrieveItemsUseCase,
            saveToDataBaseItemsUseCase,
            getItemsUseCase
        )
    }

    @Provides
    fun provideItemDetailsViewModel(getItemByIdUseCase: GetItemByIdUseCase,getLocalItemByIdUseCase: GetLocalItemByIdUseCase): IItemsDetailsViewModel {
        return ItemsDetailsViewModel(getItemByIdUseCase,getLocalItemByIdUseCase)
    }
}