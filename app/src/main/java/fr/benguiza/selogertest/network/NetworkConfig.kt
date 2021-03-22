package fr.benguiza.selogertest.network


class NetworkConfig private constructor(val baseUrl: String,
                                        val connectionTimeOut: Long = 10 * SECOND,
                                        val readTimeOut: Long = connectionTimeOut) {

    companion object {

        val PROD = NetworkConfig(baseUrl = "https://gsl-apps-technical-test.dignp.com/")

        val REGULAR = NetworkConfig(baseUrl = "https://gsl-apps-technical-test.dignp.com/",
                                    connectionTimeOut = 15 * SECOND)

        const val CACHE_SIZE = 10 * MEGA


    }
}

fun networkConfig(env: String) = when (env) {
    "prod" -> NetworkConfig.PROD
    else -> NetworkConfig.REGULAR
}

private const val SECOND = 1000L
private const val MEGA = 1024 * 1024L

