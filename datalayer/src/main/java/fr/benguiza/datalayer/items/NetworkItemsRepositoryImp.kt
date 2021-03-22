package fr.benguiza.datalayer.items

import fr.benguiza.domainlayer.item.Item
import fr.benguiza.domainlayer.item.NetworkItemsRepository
import io.reactivex.Single

class NetworkItemsRepositoryImp(private val apiService: ItemsApiService) :
    NetworkItemsRepository {

    override fun fetchItems(): Single<List<Item>> =
        apiService.getItems().map { response -> response.items.map { it.toItem() } }

    override fun getItemById(id: Int): Single<Item> =
        apiService.getItemById(id).map { it.toItem() }

}
