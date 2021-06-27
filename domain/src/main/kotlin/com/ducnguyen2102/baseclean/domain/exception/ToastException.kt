package com.ducnguyen2102.baseclean.domain.exception

import com.ducnguyen2102.baseclean.domain.base.CleanException

class ToastException(
    override val code: Int,
    override val message: String
) : CleanException(code, message)