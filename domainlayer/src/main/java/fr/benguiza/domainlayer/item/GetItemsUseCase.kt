package fr.benguiza.domainlayer.item

import io.reactivex.Observable
import fr.benguiza.commons.os.OpenWhenTesting

@OpenWhenTesting
class GetItemsUseCase (private val localItemsRepository: LocalItemsRepository) {
    fun execute(): Observable<List<Item>> = localItemsRepository.getItems()
}