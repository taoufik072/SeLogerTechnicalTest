package fr.benguiza.datalayer.items

import com.nhaarman.mockitokotlin2.mock
import fr.benguiza.domainlayer.item.NetworkItemsRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class ItemRepositoryTest {
    private lateinit var apiService: ItemsApiService
    private lateinit var data: ItemData
    private lateinit var repository: NetworkItemsRepository

    @Before
    fun setUp(){
        apiService = mock()
        data = ItemData()
        repository =  NetworkItemsRepositoryImp(apiService)
    }
    @Test
    fun getItems(){
        // Given
        `when`(apiService.getItems()).thenAnswer { Single.just(data.json.items) }
        // When
        val observer = repository.fetchItems().test()
        // Then
        observer.assertComplete()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(data.domain.items)
    }

}