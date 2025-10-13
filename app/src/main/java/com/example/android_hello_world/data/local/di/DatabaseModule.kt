package com.example.android_hello_world.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.android_hello_world.data.local.database.AppDatabase
import com.example.android_hello_world.data.local.database.AndroidhelloworldModelDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideAndroidhelloworldModelDao(appDatabase: AppDatabase): AndroidhelloworldModelDao {
        return appDatabase.androidhelloworldModelDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "AndroidhelloworldModel"
        ).build()
    }
}