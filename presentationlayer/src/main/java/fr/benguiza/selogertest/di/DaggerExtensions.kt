package fr.benguiza.selogertest.di

import android.app.Activity
import androidx.fragment.app.Fragment
import fr.benguiza.selogertest.BaseFragment
import fr.benguiza.selogertest.tools.extensions.AppBuildConfig
import fr.benguiza.selogertest.ui.item.details.ItemDetailsFragment
import fr.benguiza.selogertest.ui.item.listing.ItemsListingFragment


val Activity.injector: ActivitiesComponent?
    get() = (applicationContext as? AppComponent)?.activitiesComponent()

val Fragment.injector: FragmentsComponent?
    get() = (context?.applicationContext as? AppComponent)?.fragmentsComponent()

fun BaseFragment.inject(fragment: ItemDetailsFragment) {
    if (!AppBuildConfig.getTestingValue())
        this.injector?.inject(fragment)
}

fun BaseFragment.inject(fragment: ItemsListingFragment) {
    if (!AppBuildConfig.getTestingValue())
        this.injector?.inject(fragment)
}