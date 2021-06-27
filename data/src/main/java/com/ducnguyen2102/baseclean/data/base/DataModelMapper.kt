package com.ducnguyen2102.baseclean.data.base

import com.ducnguyen2102.baseclean.domain.base.DomainModel

interface DataModelMapper<Domain : DomainModel, Data : DataModel> {
    fun mapToDomain(dataModel: Data): Domain

    fun mapToData(domainModel: Domain): Data
}