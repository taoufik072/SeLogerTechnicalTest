package fr.benguiza.selogertest.ui.item.listing

import android.content.Context
import android.os.Bundle
import android.view.View
import fr.benguiza.selogertest.R
import fr.benguiza.selogertest.tools.extensions.cast
import fr.benguiza.selogertest.BaseFragment
import fr.benguiza.selogertest.di.inject
import javax.inject.Inject

class ItemsListingFragment : BaseFragment(R.layout.fragment_item_listing) {

    @Inject
    lateinit var viewModel: IItemsListingViewModel

    private var view: ItemsListingView? = null
    private var delegate: Delegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        delegate = context.cast()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
    }

    override fun afterViewCreated(view: View) {
        this.view = ItemsListingView(
            view = view,
            lifecycle = this,
            viewModel = viewModel,
            onItemClicked = { delegate?.showItemDetails(it) })
    }

    override fun onDetach() {
        super.onDetach()
        view = null
        delegate = null
    }

    override fun onDestroy() {
        view?.destroy()
        super.onDestroy()
    }

    interface Delegate {
        fun showItemDetails(itemId: Int)
    }
}