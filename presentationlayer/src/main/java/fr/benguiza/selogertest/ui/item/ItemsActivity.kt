package fr.benguiza.selogertest.ui.item

import androidx.navigation.findNavController
import fr.benguiza.selogertest.R
import fr.benguiza.selogertest.tools.extensions.bundle
import fr.benguiza.selogertest.BaseActivity
import fr.benguiza.selogertest.ui.item.details.ARG_ITEM_ID
import fr.benguiza.selogertest.ui.item.listing.ItemsListingFragment

class ItemsActivity : BaseActivity(R.layout.activity_item_listing),
    ItemsListingFragment.Delegate {


    override fun showItemDetails(itemId: Int) {
        findNavController(R.id.nav_host).navigate(R.id.action_itemListingFragment_to_itemDetailsFragment,
            bundle {
                putInt(
                    ARG_ITEM_ID,
                    itemId
                )
            })
    }

}