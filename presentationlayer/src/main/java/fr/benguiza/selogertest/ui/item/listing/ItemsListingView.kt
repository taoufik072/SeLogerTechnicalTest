package fr.benguiza.selogertest.ui.item.listing

import android.graphics.Rect
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.benguiza.commons.tools.Logger
import fr.benguiza.selogertest.R
import fr.benguiza.selogertest.tools.extensions.*
import fr.benguiza.selogertest.tools.views.ListAdapter
import fr.benguiza.selogertest.tools.views.ViewHolder
import fr.benguiza.selogertest.tools.views.errorView
import fr.benguiza.selogertest.ui.item.ItemUi

class ItemsListingView(
    view: View,
    lifecycle: LifecycleOwner,
    private val viewModel: IItemsListingViewModel,
    private val onItemClicked: (Int) -> Unit
) {

    private val itemRecyclerView by unsafeLazy {
        view.findViewById<RecyclerView>(
            R.id.view_items_list
        )
    }
    private val loadingView by unsafeLazy { view.findLoadingView() }
    private val errorView by unsafeLazy {
        errorView(
            view = view, title = R.string.error_title,
            icon = R.drawable.ic_error
        ) { viewModel.retry() }
    }
    private val itemsObserver = Observer<List<ItemUi>> { showItems(it) }
    private val loadingObserver =
        Observer<Boolean> { if (it) loadingView.show() else loadingView.hide() }
    private val errorObserver = Observer<Boolean> { if (it) errorView.show() else errorView.hide() }

    init {
        viewModel.observeItemsList().observe(lifecycle, itemsObserver)
        viewModel.observeLoading().observe(lifecycle, loadingObserver)
        viewModel.observeError().observe(lifecycle, errorObserver)
        viewModel.retrieveItems()
    }

    private fun showItems(items: List<ItemUi>) {
        itemRecyclerView.adapter = ListAdapter(
            items = items,
            layout = R.layout.item_item_holder,
            createViewHolder = { itemView ->
                ItemViewHolder(
                    view = itemView,
                    onItemClicked = { onItemClicked.invoke(it) })
            })

    }

    fun destroy() {
        viewModel.observeError().removeObserver(errorObserver)
        viewModel.observeLoading().removeObserver(loadingObserver)
        viewModel.observeItemsList().removeObserver(itemsObserver)
        viewModel.destroySelf()
    }
}


class ItemViewHolder(view: View, private val onItemClicked: (Int) -> Unit) :
    ViewHolder<ItemUi>(view) {
    private val itemImageView by unsafeLazy {
        view.findViewById<AppCompatImageView>(
            R.id.view_item_image
        )
    }
    private val itemCityView by unsafeLazy {
        view.findViewById<AppCompatTextView>(
            R.id.view_item_city
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

    override fun bind(item: ItemUi) {
        super.bind(item)
        with(item) {
            Glide
                .with(view.context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.holder)
                .into(itemImageView)

            itemCityView.text = city
            itemPrice.text = String.format("%.2f", price)
            itemPropertyType.text = propertyType

        }

    }

    override fun onClick(item: ItemUi, position: Int) {
        super.onClick(item, position)
        Logger.d("clicked")
        onItemClicked.invoke(item.id)
    }

    override fun unbind() {
        super.unbind()
        itemView.removeOnClickListener()
    }

    inner class GridSpacingItemDecoration(private val spanCount: Int,
                                          private val spacing: Int,
                                          private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right =
                    spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }


}