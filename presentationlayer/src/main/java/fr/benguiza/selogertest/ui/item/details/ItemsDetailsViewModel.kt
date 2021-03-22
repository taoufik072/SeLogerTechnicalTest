package fr.benguiza.selogertest.ui.item.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.benguiza.domainlayer.item.GetItemByIdUseCase
import fr.benguiza.domainlayer.item.GetLocalItemByIdUseCase
import fr.benguiza.selogertest.tools.extensions.subscribeAsyncToState
import fr.benguiza.selogertest.tools.extensions.subscribeAsyncToStateWithRetry
import fr.benguiza.selogertest.ui.item.ItemUi
import fr.benguiza.selogertest.ui.item.toUi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject

class ItemsDetailsViewModel(
    val getItemByIdUseCase: GetItemByIdUseCase,
     val getLocalItemByIdUseCase: GetLocalItemByIdUseCase
) : IItemsDetailsViewModel {

    private val itemDetails = MutableLiveData<ItemUi>()
    private val errorState = MutableLiveData<Boolean>()
    private val loadingState = MutableLiveData<Boolean>()

    private val disposables = CompositeDisposable()
    private val retry = PublishSubject.create<Unit>()

    /* ------------------------------------- */
    /*                Override               */
    /* ------------------------------------- */

    override fun observeItemDetails(): LiveData<ItemUi> = itemDetails
    override fun observeError(): LiveData<Boolean> = errorState
    override fun observeLoading(): LiveData<Boolean> = loadingState

    /* ------------------------------------- */
    /*              Processing               */
    /* ------------------------------------- */
    override fun fetchItemDetails(itemId: Int) {
        disposables += getItemByIdUseCase.execute(id = itemId)
            .subscribeAsyncToStateWithRetry(
                onSuccess = {
                    loadingState.postValue(false)
                    errorState.postValue(false)
                    itemDetails.postValue(it.toUi())
                },
                onLoading = {
                    errorState.postValue(false)
                    loadingState.postValue(true)
                },
                onError = {
                    tryFromLocalItem(itemId)
                },
                retry = retry
            )

    }

    private fun tryFromLocalItem(itemId: Int) {
        disposables += getLocalItemByIdUseCase.execute(id = itemId)
            .subscribeAsyncToState(
                onLoading = { loadingState.postValue(true) },
                onSuccess = {
                    loadingState.postValue(false)
                    errorState.postValue(false)
                    itemDetails.postValue(it.toUi())
                },
                onError = {
                    loadingState.postValue(false)
                    errorState.postValue(true)
                })
    }

    override fun retry() {
        retry.onNext(Unit)
        errorState.postValue(false)
    }

    override fun destroySelf() {
        disposables.clear()
    }

}