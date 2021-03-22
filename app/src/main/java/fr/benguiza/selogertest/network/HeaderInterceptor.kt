package fr.benguiza.selogertest.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(chain.request().newBuilder()
                                                                                       .addHeader(ACCEPT_KEY, ACCEPT_VALUE)
                                                                                       .addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
                                                                                       .build())
}

const val ACCEPT_KEY = "Accept"
const val ACCEPT_VALUE = "application/json"
const val CONTENT_TYPE_KEY = "Content-Type"
const val CONTENT_TYPE_VALUE = "application/json"
