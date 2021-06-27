package com.ducnguyen2102.baseclean.data.base

interface RoomEntityMapper<Data : DataModel, Entity : RoomEntity> {
    fun mapToEntity(dataModel: Data): Entity

    fun mapToData(entity: Entity): Data
}