package ru.yotfr.unisoldevtest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.storageloader.StorageLoaderImpl
import ru.yotfr.storageloader.StorageLoader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageLoaderModule {

    @Provides
    @Singleton
    fun provideStorageLoader(
        @ApplicationContext context: Context
    ): ru.yotfr.storageloader.StorageLoader {
        return ru.yotfr.storageloader.StorageLoaderImpl(context)
    }
}