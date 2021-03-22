package fr.benguiza.commons_android

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object TSchedulers {

    lateinit var io: Scheduler
        private set
    lateinit var computation: Scheduler
        private set
    lateinit var trampoline: Scheduler
        private set
    lateinit var ui: Scheduler
        private set

    fun init(computation: Scheduler = Schedulers.computation(),
             io: Scheduler = Schedulers.io(),
             trampoline: Scheduler = Schedulers.trampoline(),
             ui: Scheduler = AndroidSchedulers.mainThread()) {
        TSchedulers.io = io
        TSchedulers.computation = computation
        TSchedulers.trampoline = trampoline
        TSchedulers.ui = ui
    }
}

fun TSchedulers.initForTest() {
    init(io = Schedulers.trampoline(),
         computation = Schedulers.trampoline(),
         trampoline = Schedulers.trampoline(),
         ui = Schedulers.trampoline())
}