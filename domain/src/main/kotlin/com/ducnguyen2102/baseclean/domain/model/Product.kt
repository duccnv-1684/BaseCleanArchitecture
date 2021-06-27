package com.ducnguyen2102.baseclean.domain.model

import com.ducnguyen2102.baseclean.domain.base.DomainModel

data class Product(
    val id: Int,
    val errorDescription: String,
    val name: String,
    val sku: String,
    val image: String,
    val color: Color
) : DomainModel