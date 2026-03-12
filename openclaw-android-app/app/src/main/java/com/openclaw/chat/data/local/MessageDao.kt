package com.openclaw.chat.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Message entities
 * Provides methods to interact with the local database
 */
@Dao
interface MessageDao {
    
    /**
     * Get all messages as Flow (reactive)
     * Ordered by timestamp ascending
     */
    @Query("SELECT * FROM messages ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<MessageEntity>>
    
    /**
     * Get last N messages
     */
    @Query("SELECT * FROM messages ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getLastMessages(limit: Int = 50): List<MessageEntity>
    
    /**
     * Insert a single message
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: MessageEntity)
    
    /**
     * Insert multiple messages
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(messages: List<MessageEntity>)
    
    /**
     * Update a message (e.g., update status)
     */
    @Update
    suspend fun update(message: MessageEntity)
    
    /**
     * Delete a message
     */
    @Delete
    suspend fun delete(message: MessageEntity)
    
    /**
     * Delete all messages
     */
    @Query("DELETE FROM messages")
    suspend fun deleteAll()
    
    /**
     * Delete messages older than timestamp
     */
    @Query("DELETE FROM messages WHERE timestamp < :olderThan")
    suspend fun deleteOlderThan(olderThan: Long)
    
    /**
     * Get message count
     */
    @Query("SELECT COUNT(*) FROM messages")
    suspend fun getCount(): Int
    
    /**
     * Search messages by content
     */
    @Query("SELECT * FROM messages WHERE content LIKE :query ORDER BY timestamp DESC")
    fun searchMessages(query: String): Flow<List<MessageEntity>>
}
