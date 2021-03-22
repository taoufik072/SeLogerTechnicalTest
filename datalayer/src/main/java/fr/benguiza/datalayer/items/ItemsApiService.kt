package fr.benguiza.datalayer.items

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface ItemsApiService {

    @GET("listings.json")
    fun getItems(): Single<ItemsJson>

    @GET("listings/{listingId}.json")
    fun getItemById(@Path("listingId") id: Int): Single<ItemJson>
}