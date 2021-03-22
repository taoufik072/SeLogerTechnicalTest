package fr.benguiza.selogertest.ui.item.details

import androidx.lifecycle.LiveData
import fr.benguiza.selogertest.ui.item.ItemUi

interface IItemsDetailsViewModel {
    fun observeItemDetails(): LiveData<ItemUi>
    fun observeError(): LiveData<Boolean>
    fun observeLoading(): LiveData<Boolean>
    fun fetchItemDetails(itemId: Int)
    fun retry()
    fun destroySelf()
}