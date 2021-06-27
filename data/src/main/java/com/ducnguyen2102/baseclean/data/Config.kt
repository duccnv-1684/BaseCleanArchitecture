package com.ducnguyen2102.baseclean.data

object Config {
    const val BASE_URL = "https://60ae0d5e80a61f00173324bc.mockapi.io/"
    const val DATABASE_NAME = "jasonapp.db"
    const val DATABASE_VERSION = 1
}

object HttpClient {
    const val CONNECT_TIMEOUT = 10L
    const val READ_TIMEOUT = 10L
    const val WRITE_TIMEOUT = 10L
}

object Constants {
    const val DATABASE_PAGING_SIZE = 10
}

object ErrorCode {
    const val NETWORK = 1001
    const val UNKNOWN = 1002
}