package fr.benguiza.selogertest.tools.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.benguiza.commons_android.BuildConfig
import fr.benguiza.selogertest.R


// View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.removeOnClickListener() {
    setOnClickListener(null)
}

fun View.findErrorView(): View = findViewById(R.id.error_view)
fun View.findLoadingView(): View = findViewById(R.id.loading_view)

// ViewGroup
fun ViewGroup.inflate(layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)
