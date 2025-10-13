package com.example.android_hello_world.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AndroidhelloworldModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun androidhelloworldModelDao(): AndroidhelloworldModelDao
}