package fr.benguiza.domainlayer.item

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

class UseCaseTest {
    @Test
    fun test() {
        // Given
        val data = ItemDomain()
        val repository = mock<NetworkItemsRepository> {
            given(it.fetchItems()).willReturn(Single.just(data.items))
        }
        val useCase = RetrieveItemsUseCase(repository)
        val expected = data.items
        // When
        val testObserver = useCase.execute().test()
        // Then
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue(expected)
    }
}