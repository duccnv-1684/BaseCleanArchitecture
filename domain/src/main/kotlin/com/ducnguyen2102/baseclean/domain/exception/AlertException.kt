package com.ducnguyen2102.baseclean.domain.exception

import com.ducnguyen2102.baseclean.domain.base.CleanException

class AlertException(
    override val code: Int,
    override val message: String,
    val title: String? = null
) : CleanException(code, message)