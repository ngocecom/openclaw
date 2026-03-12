package com.openclaw.chat.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Message entity for Room database
 * Represents a message stored locally
 */
@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: Long,
    val content: String,
    val isFromUser: Boolean,
    val timestamp: Long,
    val status: String, // Stored as string for Room
    val sessionId: String? = null,
    val tokens: Int? = null,
    val model: String? = null,
    val duration: Long? = null,
    val error: String? = null
)
