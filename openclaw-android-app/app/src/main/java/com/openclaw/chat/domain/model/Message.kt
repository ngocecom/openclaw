package com.openclaw.chat.domain.model

/**
 * Message data model
 * Represents a chat message in the conversation
 */
data class Message(
    val id: Long = System.currentTimeMillis(),
    val content: String,
    val isFromUser: Boolean, // true = user message, false = OpenClaw response
    val timestamp: Long = System.currentTimeMillis(),
    val status: MessageStatus = MessageStatus.SENT,
    val metadata: MessageMetadata? = null
)

/**
 * Message status
 */
enum class MessageStatus {
    SENDING,    // Message is being sent
    SENT,       // Message sent successfully
    DELIVERED,  // Message delivered to OpenClaw
    READ,       // Message read by OpenClaw
    FAILED      // Message failed to send
}

/**
 * Message metadata
 * Additional information about the message
 */
data class MessageMetadata(
    val sessionId: String? = null,
    val tokens: Int? = null,
    val model: String? = null,
    val duration: Long? = null, // Processing time in ms
    val error: String? = null   // Error message if failed
)

/**
 * Conversation state
 */
data class ConversationState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isConnected: Boolean = false
)
