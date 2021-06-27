package com.ducnguyen2102.baseclean.data.repository

import android.content.Context
import com.ducnguyen2102.baseclean.data.local.room.dao.ColorDao
import com.ducnguyen2102.baseclean.data.local.room.entities.ColorEntityMapper
import com.ducnguyen2102.baseclean.data.model.DataColorMapper
import com.ducnguyen2102.baseclean.data.remote.api.ColorApi
import com.ducnguyen2102.baseclean.data.remote.factory.handleResponse
import com.ducnguyen2102.baseclean.data.remote.response.ColorResponseMapper
import com.ducnguyen2102.baseclean.domain.model.Color
import com.ducnguyen2102.baseclean.domain.repository.ColorRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ColorRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val colorApi: ColorApi,
    private val colorDao: ColorDao,
    private val colorResponseMapper: ColorResponseMapper,
    private val colorEntityMapper: ColorEntityMapper,
    private val dataColorMapper: DataColorMapper
) : ColorRepository {
    override suspend fun getMockupColors() {
        if (colorDao.getColors().isEmpty()) {
            handleResponse(context, colorApi.getColors()) {
                it.map { colorResponseMapper.mapToData(it) }
                    .map { colorEntityMapper.mapToEntity(it) }
                    .let { colorDao.insert(it) }
            }
        }
    }

    override suspend fun getAllColors(): List<Color> {
        return colorDao.getColors().map { colorEntityMapper.mapToData(it) }.map { dataColorMapper.mapToDomain(it) }
    }
}