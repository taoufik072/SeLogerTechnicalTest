package fr.benguiza.domainlayer.item

import io.reactivex.Observable

class GetItemByIdUseCase(private val repository: NetworkItemsRepository) {
    fun execute(id: Int): Observable<Item> = repository.getItemById(id).toObservable()
}