package fr.benguiza.domainlayer.item

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface LocalItemsRepository {
    fun saveToDataBaseItems(items: List<Item>): Completable
    fun getItemById(id:Int): Single<Item>
    fun getItems(): Observable<List<Item>>
}