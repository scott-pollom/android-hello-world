package com.example.android_hello_world.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.example.android_hello_world.data.AndroidhelloworldModelRepository
import com.example.android_hello_world.data.DefaultAndroidhelloworldModelRepository
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsAndroidhelloworldModelRepository(
        androidhelloworldModelRepository: DefaultAndroidhelloworldModelRepository
    ): AndroidhelloworldModelRepository
}

class FakeAndroidhelloworldModelRepository @Inject constructor() :
    AndroidhelloworldModelRepository {
    override val myModels: Flow<List<String>> = flowOf(fakeMyModels)

    override suspend fun add(name: String) {
        throw NotImplementedError()
    }
}

val fakeMyModels = listOf("One", "Two", "Three")