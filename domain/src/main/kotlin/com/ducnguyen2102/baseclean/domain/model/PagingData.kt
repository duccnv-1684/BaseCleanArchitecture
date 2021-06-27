package com.ducnguyen2102.baseclean.domain.model

import com.ducnguyen2102.baseclean.domain.base.DomainModel

class PagingData<Model : DomainModel>(
    val data: List<Model>,
    val isEnded: Boolean
)