package com.ducnguyen2102.baseclean.base

import com.ducnguyen2102.baseclean.domain.base.DomainModel

interface PresentationModelMapper<Domain : DomainModel, Presentation : PresentationModel> {
    fun mapToDomain(presentationModel: Presentation): Domain

    fun mapToPresentation(domainModel: Domain): Presentation
}