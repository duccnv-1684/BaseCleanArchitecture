package com.ducnguyen2102.baseclean.domain.base

open class CleanException(
    open val code: Int,
    override val message: String?
) : Throwable(message)