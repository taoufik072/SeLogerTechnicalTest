package fr.benguiza.datalayer.items

import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.queryAsSingle
import com.vicpin.krealmextensions.saveAll
import fr.benguiza.domainlayer.item.Item
import fr.benguiza.domainlayer.item.LocalItemsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class LocalItemsRepositoryImp : LocalItemsRepository {
    override fun saveToDataBaseItems(items: List<Item>): Completable {
        return Completable.fromCallable { items.map { it.toItemEntity() }.saveAll()  }
    }

    override fun getItemById(id: Int): Single<Item> {
        return ItemEntity().queryAsSingle { equalTo("id", id) }.map { it.first().toItem() }
    }

    override fun getItems(): Observable<List<Item>> {
        return Observable.just(ItemEntity().queryAll().map { it.toItem() })
    }

}