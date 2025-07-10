package nam.tran.home.assignment.jetpack.compose.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nam.tran.home.assignment.jetpack.compose.data.local.db.AppDatabase
import nam.tran.home.assignment.jetpack.compose.data.local.db.ProductDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "assignment_database"
        ).build()
    }

    @Provides
    fun provideProductDao(
        database: AppDatabase
    ): ProductDao = database.productDao()
}