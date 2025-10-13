package com.example.android_hello_world.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.android_hello_world.data.local.database.AndroidhelloworldModel
import com.example.android_hello_world.data.local.database.AndroidhelloworldModelDao
import javax.inject.Inject

interface AndroidhelloworldModelRepository {
    val myModels: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultAndroidhelloworldModelRepository @Inject constructor(
    private val androidhelloworldModelDao: AndroidhelloworldModelDao
) : AndroidhelloworldModelRepository {

    override val myModels: Flow<List<String>> =
        androidhelloworldModelDao.getAndroidhelloworldModels()
            .map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        androidhelloworldModelDao.insertAndroidhelloworldModel(AndroidhelloworldModel(name = name))
    }
}