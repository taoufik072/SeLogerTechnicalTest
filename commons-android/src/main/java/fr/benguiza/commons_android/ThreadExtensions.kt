package fr.benguiza.commons_android

import io.reactivex.*

fun <T> Single<T>.apiCall(): Single<T> = subscribeOn(TSchedulers.io).observeOn(TSchedulers.ui)

fun <T> Single<T>.asyncToMainThread(): Single<T> = subscribeOn(TSchedulers.io).observeOn(TSchedulers.ui)

fun <T>Observable<T>.ioToMainThread(): Observable<T> = subscribeOn(TSchedulers.io).observeOn(TSchedulers.ui)

fun <T> Flowable<T>.apiCall(): Flowable<T> = subscribeOn(TSchedulers.io).observeOn(TSchedulers.ui)
