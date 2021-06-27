package com.ducnguyen2102.baseclean.domain.repository

import com.ducnguyen2102.baseclean.domain.base.Repository
import com.ducnguyen2102.baseclean.domain.model.Color

interface ColorRepository : Repository {
    suspend fun getMockupColors()

    suspend fun getAllColors(): List<Color>
}