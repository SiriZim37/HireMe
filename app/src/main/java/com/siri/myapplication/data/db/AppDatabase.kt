package com.siri.myapplication.data.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.siri.myapplication.data.dao.MasterServiceDao
import com.siri.myapplication.data.dao.UserServiceDao
import com.siri.myapplication.data.dao.UserDao
import com.siri.myapplication.data.dao.DeviceDao
import com.siri.myapplication.data.dao.WorkingDaysDao
import com.siri.myapplication.data.entity.Converters
import com.siri.myapplication.data.entity.MasterServiceEntity
import com.siri.myapplication.data.entity.UserEntity
import com.siri.myapplication.data.entity.DeviceEntity
import com.siri.myapplication.data.entity.UserServiceEntity
import com.siri.myapplication.data.entity.WorkingDaysEntity

@Database(entities = [UserEntity::class,
                      UserServiceEntity::class,
                      DeviceEntity::class ,
                      WorkingDaysEntity::class ,
                      MasterServiceEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun serviceDao(): UserServiceDao
    abstract fun deviceDao(): DeviceDao
    abstract fun workingDayDao(): WorkingDaysDao
    abstract fun masterServiceDao(): MasterServiceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "appdb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
