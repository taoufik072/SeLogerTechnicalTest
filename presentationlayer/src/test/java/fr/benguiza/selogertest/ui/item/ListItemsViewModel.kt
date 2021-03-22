package fr.benguiza.selogertest.ui.item

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import fr.benguiza.commons_android.TSchedulers
import fr.benguiza.commons_android.initForTest
import fr.benguiza.domainlayer.item.GetItemsUseCase
import fr.benguiza.domainlayer.item.RetrieveItemsUseCase
import fr.benguiza.domainlayer.item.SaveToDataBaseItemsUseCase
import fr.benguiza.selogertest.ui.item.listing.ItemsListingViewModel
import io.mockk.*
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.*
import java.lang.Exception
import kotlin.test.assertEquals

class ListItemsViewModel {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: ItemsListingViewModel = mockk()
    private lateinit var data: ItemUiData
    private var retrieveItemsUseCase: RetrieveItemsUseCase = mockk()
    private var saveToDataBase: SaveToDataBaseItemsUseCase = mockk()
    private var getItemsUseCase: GetItemsUseCase = mockk()

    private var resultObserver: Observer<List<ItemUi>> = spyk()
    private var loadingObserver: Observer<Boolean> =  spyk()
    private var errorObserver: Observer<Boolean> =  spyk()

    @Before
    fun setUp() {
        data = ItemUiData()

        TSchedulers.initForTest()

        every { saveToDataBase.execute(data.domain.items) } returns Completable.complete()

        viewModel =
            ItemsListingViewModel(
                retrieveItemsUseCase,
                saveToDataBase,
                getItemsUseCase
            )

        viewModel.observeItemsList().observeForever(resultObserver)
        viewModel.observeLoading().observeForever(loadingObserver)
        viewModel.observeError().observeForever(errorObserver)
    }

    @Test
    fun getItemsSuccess() {

        every { retrieveItemsUseCase.execute() } returns Observable.just(data.domain.items)
        every { getItemsUseCase.execute() } returns Observable.just(data.domain.items)

        viewModel.retrieveItems()

        verify(exactly = 1) { resultObserver.onChanged(any()) }
        verify(exactly = 3) { loadingObserver.onChanged(any()) }
        verify(exactly = 0) { errorObserver.onChanged(true) }

        assertEquals(data.ui.items, viewModel.observeItemsList().value)

    }

    @Test
    fun getItemsError() {
        every { retrieveItemsUseCase.execute() } returns Observable.error(Exception())
        every { getItemsUseCase.execute() } returns Observable.error(Exception())

        viewModel.retrieveItems()


        verify(exactly = 3) { loadingObserver.onChanged(any()) }
        verify(exactly = 1) { errorObserver.onChanged(true) }


    }


    @After
    fun tearDown() {
        unmockkAll()
    }

}