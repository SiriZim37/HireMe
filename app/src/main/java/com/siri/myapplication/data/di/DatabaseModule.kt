package com.siri.myapplication.data.di

import android.content.Context
import com.siri.myapplication.data.db.AppDatabase
import com.siri.myapplication.data.dao.MasterServiceDao
import com.siri.myapplication.data.dao.UserServiceDao
import com.siri.myapplication.data.dao.WorkingDaysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideMasterServiceDao(appContext: Context): MasterServiceDao {
        return AppDatabase.getDatabase(appContext).masterServiceDao()
    }

    @Provides
    fun provideUserServiceDao(appDatabase: AppDatabase): UserServiceDao {
        return appDatabase.serviceDao()
    }

    @Provides
    fun provideWorkingDaysDao(appDatabase: AppDatabase): WorkingDaysDao {
        return appDatabase.workingDayDao()
    }
}
