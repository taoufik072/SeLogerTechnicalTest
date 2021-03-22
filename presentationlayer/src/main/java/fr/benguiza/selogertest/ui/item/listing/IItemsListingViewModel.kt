package fr.benguiza.selogertest.ui.item.listing

import androidx.lifecycle.LiveData
import fr.benguiza.selogertest.ui.item.ItemUi

interface IItemsListingViewModel {
    fun observeItemsList(): LiveData<List<ItemUi>>
    fun observeError(): LiveData<Boolean>
    fun observeLoading(): LiveData<Boolean>
    fun retry()
    fun destroySelf()
    fun retrieveItems()
}