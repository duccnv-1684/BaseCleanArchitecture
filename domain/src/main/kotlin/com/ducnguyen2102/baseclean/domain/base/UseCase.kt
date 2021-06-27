package com.ducnguyen2102.baseclean.domain.base

abstract class UseCase<in Param, out Result> where Param : UseCase.Param, Result : Any? {
    abstract suspend fun execute(param: Param): Result

    open class Param
}