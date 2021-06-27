package com.ducnguyen2102.baseclean.domain.usecase.color

import com.ducnguyen2102.baseclean.domain.base.UseCase
import com.ducnguyen2102.baseclean.domain.model.Color
import com.ducnguyen2102.baseclean.domain.repository.ColorRepository
import javax.inject.Inject

class GetAllColorUseCase @Inject constructor(
    private val colorRepository: ColorRepository,
) : UseCase<UseCase.Param, List<Color>>() {
    override suspend fun execute(param: Param): List<Color> {
        return colorRepository.getAllColors()
    }
}