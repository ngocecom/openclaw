package com.openclaw.chat.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Room Database for OpenClaw Chat
 * Stores message history locally
 */
@Database(
    entities = [MessageEntity::class],
    version = 1,
    exportSchema = false
)
@Singleton
abstract class ChatDatabase : RoomDatabase() {
    
    /**
     * Get Message DAO
     */
    abstract fun messageDao(): MessageDao
    
    companion object {
        @Volatile private var INSTANCE: ChatDatabase? = null
        
        /**
         * Get database instance (Singleton pattern)
         */
        fun getInstance(context: Context): ChatDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java,
                    "openclaw_chat_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
