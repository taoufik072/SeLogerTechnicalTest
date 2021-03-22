package fr.benguiza.domainlayer.item

import io.reactivex.Single

interface NetworkItemsRepository {
    fun fetchItems(): Single<List<Item>>
    fun getItemById(id:Int): Single<Item>
}