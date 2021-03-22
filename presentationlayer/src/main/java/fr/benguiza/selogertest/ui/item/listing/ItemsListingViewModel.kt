package fr.benguiza.selogertest.ui.item.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.benguiza.commons.tools.Logger
import fr.benguiza.domainlayer.item.GetItemsUseCase
import fr.benguiza.domainlayer.item.Item
import fr.benguiza.domainlayer.item.RetrieveItemsUseCase
import fr.benguiza.domainlayer.item.SaveToDataBaseItemsUseCase
import fr.benguiza.selogertest.tools.extensions.subscribeAsyncToState
import fr.benguiza.selogertest.tools.extensions.subscribeAsyncToStateWithRetry
import fr.benguiza.selogertest.ui.item.ItemUi
import fr.benguiza.selogertest.ui.item.toUi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject

class ItemsListingViewModel(
    private val retrieveItemsUseCase: RetrieveItemsUseCase,
    private val saveToDataBase: SaveToDataBaseItemsUseCase,
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel(), IItemsListingViewModel {

    private val disposables = CompositeDisposable()
    private val retry = PublishSubject.create<Unit>()

    private val itemsList = MutableLiveData<List<ItemUi>>()
    private val errorState = MutableLiveData<Boolean>()
    private val loadingState = MutableLiveData<Boolean>()

    /* ------------------------------------- */
    /*                Override               */
    /* ------------------------------------- */

    override fun observeItemsList(): LiveData<List<ItemUi>> = itemsList
    override fun observeError(): LiveData<Boolean> = errorState
    override fun observeLoading(): LiveData<Boolean> = loadingState


    /* ------------------------------------- */
    /*              Processing               */
    /* ------------------------------------- */


    override fun retrieveItems() {
        disposables += retrieveItemsUseCase.execute()
            .subscribeAsyncToStateWithRetry(
                onSuccess = { saveItems(it) },
                onLoading = { loadingState.postValue(true) },
                onError = { getItemsFromDataBase() },
                retry = retry
            )
    }


    private fun saveItems(items: List<Item>) {
        disposables += saveToDataBase.execute(items)
            .subscribeAsyncToState(
                onSuccess = {
                    loadingState.postValue(false)
                    itemsList.postValue(items.map { it.toUi() })
                },
                onError = {
                    Logger.e(it)
                    errorState.postValue(true)
                },
                onLoading = { loadingState.postValue(true) })
    }

    private fun getItemsFromDataBase() {
        disposables += getItemsUseCase.execute()
            .subscribeAsyncToState(
                onSuccess = {
                    loadingState.postValue(false)
                    if (it.isEmpty()) {
                        errorState.postValue(true)
                    } else {
                        val items = it.map { items -> items.toUi() }
                        itemsList.postValue(items)
                        errorState.postValue(false)
                    }
                },
                onLoading = {
                    loadingState.postValue(true)
                    errorState.postValue(false)
                }
                ,
                onError = {
                    loadingState.postValue(false)
                    errorState.postValue(true)
                }
            )


    }


    override fun retry() {
        retry.onNext(Unit)
        errorState.postValue(false)
    }

    override fun destroySelf() {
        disposables.clear()
    }
}