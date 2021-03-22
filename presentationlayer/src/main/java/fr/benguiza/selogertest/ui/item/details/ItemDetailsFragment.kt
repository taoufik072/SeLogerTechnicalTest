package fr.benguiza.selogertest.ui.item.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.benguiza.selogertest.R
import fr.benguiza.selogertest.BaseFragment
import fr.benguiza.selogertest.di.inject
import javax.inject.Inject

class ItemDetailsFragment : BaseFragment(R.layout.fragment_item_details) {

    @Inject
    lateinit var viewModel: IItemsDetailsViewModel
    private var view: ItemsDetailsView? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
    }



    override fun afterViewCreated(view: View) {
        inject(this)
        this.view = ItemsDetailsView(
            view = view, viewModel = viewModel, lifecycle = this,
            itemId = requireArguments().getInt(ARG_ITEM_ID)
        )
    }

    override fun onDestroy() {
        view?.onDestroy()
        super.onDestroy()
    }
    override fun onDetach() {
        super.onDetach()
        view = null
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}

const val ARG_ITEM_ID = ":ARG_ITEM_ID"