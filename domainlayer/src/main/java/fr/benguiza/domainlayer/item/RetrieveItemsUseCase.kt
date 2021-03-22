package fr.benguiza.domainlayer.item

import fr.benguiza.commons.os.OpenWhenTesting
import io.reactivex.Observable
import io.reactivex.Single


@OpenWhenTesting
class RetrieveItemsUseCase(private val repository: NetworkItemsRepository) {
    fun execute() : Observable<List<Item>> = repository.fetchItems().toObservable()
}