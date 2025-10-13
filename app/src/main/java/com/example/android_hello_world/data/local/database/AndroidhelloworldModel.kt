package com.example.android_hello_world.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class AndroidhelloworldModel(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Dao
interface AndroidhelloworldModelDao {
    @Query("SELECT * FROM androidhelloworldmodel ORDER BY uid DESC LIMIT 10")
    fun getAndroidhelloworldModels(): Flow<List<AndroidhelloworldModel>>

    @Insert
    suspend fun insertAndroidhelloworldModel(item: AndroidhelloworldModel)
}