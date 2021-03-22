package fr.benguiza.selogertest.tools.extensions

import android.os.Bundle

fun bundle(init: Bundle.() -> Unit) = Bundle().apply(init)