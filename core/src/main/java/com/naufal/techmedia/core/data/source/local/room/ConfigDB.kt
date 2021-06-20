package com.naufal.techmedia.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naufal.techmedia.core.data.source.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class ConfigDB : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}