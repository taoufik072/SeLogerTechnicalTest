package fr.benguiza.selogertest.ui.item.details

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import fr.benguiza.selogertest.R
import fr.benguiza.selogertest.tools.extensions.findLoadingView
import fr.benguiza.selogertest.tools.extensions.makeGone
import fr.benguiza.selogertest.tools.extensions.show
import fr.benguiza.selogertest.tools.extensions.unsafeLazy
import fr.benguiza.selogertest.tools.views.errorView
import fr.benguiza.selogertest.ui.item.ItemUi

class ItemsDetailsView(
    private val view: View,
    itemId: Int,
    private val viewModel: IItemsDetailsViewModel,
    lifecycle: LifecycleOwner
) {

    private val itemImageView by unsafeLazy {
        view.findViewById<ImageView>(
            R.id.view_item_image
        )
    }
    private val itemCityView by unsafeLazy {
        view.findViewById<AppCompatTextView>(
            R.id.city_text
        )
    }
    private val itemRoomsView by unsafeLazy {
        view.findViewById<AppCompatTextView>(
            R.id.view_item_property_rooms
        )
    }
    private val itemAreaView by unsafeLazy {
        view.findViewById<AppCompatTextView>(
            R.id.view_item_area
        )
    }
    private val itemBedroomsView by unsafeLazy {
        view.findViewById<AppCompatTextView>(
            R.id.view_item_property_bedrooms
        )
    }
    private val itemPropertyType by unsafeLazy {
        view.findViewById<AppCompatTextView>(
            R.id.view_item_property_type
        )
    }
    private val itemPrice by unsafeLazy {
        view.findViewById<AppCompatTextView>(
            R.id.view_item_price
        )
    }
    private val itemProfessional by unsafeLazy {
        view.findViewById<AppCompatTextView>(
            R.id.view_item_professional
        )
    }
    private val loadingView by unsafeLazy { view.findLoadingView() }
    private val errorView by unsafeLazy {
        errorView(
            view = view, title = R.string.error_title,
            icon = R.drawable.ic_error
        ) { viewModel.retry() }
    }


    private val itemDetailObserver = Observer<ItemUi> {
        if (it != null) fillItemDetails(it)
    }

    private val errorStateObserver = Observer<Boolean> {
        if (it == true) errorView.show() else errorView.hide()
    }
    private val loadingStateObserver = Observer<Boolean> {
        if (it == true) loadingView.show() else loadingView.makeGone()
    }

    init {
        viewModel.observeLoading().observe(lifecycle, loadingStateObserver)
        viewModel.observeError().observe(lifecycle, errorStateObserver)
        viewModel.observeItemDetails().observe(lifecycle, itemDetailObserver)
        viewModel.fetchItemDetails(itemId)
    }

    private fun fillItemDetails(itemUi: ItemUi) {
        with(itemUi)
        {
            Glide
                .with(view.context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.holder)
                .into(itemImageView)

            itemCityView.text = city
            itemPrice.text = String.format("%.2f", price)
            itemBedroomsView.text =
                view.resources.getQuantityString(R.plurals.bedroom_text, bedrooms, bedrooms)
            itemRoomsView.text = view.resources.getQuantityString(R.plurals.room_text, rooms, rooms)
            itemAreaView.text = String.format("%.2f", area)
            itemPropertyType.text =
                view.resources.getString(R.string.propertyType_text, propertyType)
            itemProfessional.text = professional
        }
    }

    fun onDestroy() {
        viewModel.observeItemDetails().removeObserver(itemDetailObserver)
        viewModel.observeError().removeObserver(errorStateObserver)
        viewModel.observeLoading().removeObserver(loadingStateObserver)
        viewModel.destroySelf()
    }
}