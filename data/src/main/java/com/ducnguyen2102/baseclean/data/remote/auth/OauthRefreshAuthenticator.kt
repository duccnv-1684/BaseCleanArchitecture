package com.ducnguyen2102.baseclean.data.remote.auth

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class OauthRefreshAuthenticator @Inject constructor() : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request {
        return response.request
    }
}