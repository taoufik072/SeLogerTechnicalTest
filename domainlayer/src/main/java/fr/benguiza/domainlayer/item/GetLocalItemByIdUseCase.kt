package fr.benguiza.domainlayer.item

import io.reactivex.Observable
import io.reactivex.Single

class GetLocalItemByIdUseCase(private val localItemsRepository: LocalItemsRepository) {
    fun execute(id: Int): Observable<Item> = localItemsRepository.getItemById(id).toObservable()
}