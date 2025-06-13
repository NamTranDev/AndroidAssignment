package nam.tran.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.data.local.LocalInfoManagerImpl
import nam.tran.data.local.db.DatabaseModule
import nam.tran.data.local.db.ProductDao
import nam.tran.data.network.NetModule
import nam.tran.data.repository.BookmarkRepositoryImpl
import nam.tran.domain.manager.LocalInfoManager
import nam.tran.domain.repository.BookmarkRepository
import javax.inject.Singleton

@Module(includes = [NetModule::class, DatabaseModule::class,])
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideLocalInfoManager(
        application: Application,
    ): LocalInfoManager = LocalInfoManagerImpl(application)

    @Provides
    @Singleton
    fun provideBookmarkRepository(
        productDao: ProductDao
    ): BookmarkRepository = BookmarkRepositoryImpl(productDao)
}