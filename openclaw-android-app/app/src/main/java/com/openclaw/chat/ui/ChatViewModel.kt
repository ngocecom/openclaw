package com.openclaw.chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclaw.chat.data.local.MessageDao
import com.openclaw.chat.data.local.MessageEntity
import com.openclaw.chat.domain.model.ConversationState
import com.openclaw.chat.domain.model.Message
import com.openclaw.chat.domain.model.MessageStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * ViewModel for Chat Screen
 * Handles business logic and state management
 * WITH ROOM DATABASE - Full persistence
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageDao: MessageDao
) : ViewModel() {
    
    private val _state = MutableStateFlow(ConversationState())
    val state: StateFlow<ConversationState> = _state.asStateFlow()
    
    // Session ID for this conversation
    private val sessionId = UUID.randomUUID().toString()
    
    // Connection state (simulated for now)
    private var isConnected = true
    
    init {
        // Load message history from database
        loadMessages()
    }
    
    /**
     * Load messages from local database
     */
    private fun loadMessages() {
        viewModelScope.launch {
            messageDao.getAllMessages().collect { entities ->
                val messages = entities.map { it.toDomainModel() }
                _state.value = _state.value.copy(
                    messages = messages,
                    isConnected = isConnected
                )
            }
        }
    }
    
    /**
     * Send a message to OpenClaw
     */
    fun sendMessage(content: String) {
        if (!isConnected) {
            _state.value = _state.value.copy(
                error = "Not connected to OpenClaw"
            )
            return
        }
        
        viewModelScope.launch {
            // Create user message
            val userMessage = Message(
                content = content,
                isFromUser = true,
                status = MessageStatus.SENDING
            )
            
            // Save to database
            messageDao.insert(userMessage.toEntity())
            
            // Update state to sending
            _state.value = _state.value.copy(
                isLoading = true,
                error = null
            )
            
            try {
                // TODO: Send to OpenClaw API
                // For now, simulate response
                simulateOpenClawResponse(content)
            } catch (e: Exception) {
                // Update message status to failed
                val failedMessage = userMessage.copy(
                    status = MessageStatus.FAILED,
                    metadata = userMessage.metadata?.copy(error = e.message)
                )
                messageDao.insert(failedMessage.toEntity())
                
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Failed to send message: ${e.message}"
                )
            }
        }
    }
    
    /**
     * Simulate OpenClaw response (placeholder for actual API call)
     */
    private suspend fun simulateOpenClawResponse(userMessage: String) {
        // Simulate network delay
        kotlinx.coroutines.delay(1000)
        
        // Generate response (placeholder)
        val response = generatePlaceholderResponse(userMessage)
        
        // Create assistant message
        val assistantMessage = Message(
            content = response,
            isFromUser = false,
            status = MessageStatus.DELIVERED,
            metadata = com.openclaw.chat.domain.model.MessageMetadata(
                sessionId = sessionId,
                model = "OpenClaw",
                duration = 1000L
            )
        )
        
        // Save to database
        messageDao.insert(assistantMessage.toEntity())
        
        // Update state
        _state.value = _state.value.copy(
            isLoading = false
        )
    }
    
    /**
     * Generate placeholder response (to be replaced with actual API call)
     */
    private fun generatePlaceholderResponse(userMessage: String): String {
        return when {
            userMessage.contains("hello", ignoreCase = true) || 
            userMessage.contains("hi", ignoreCase = true) -> 
                "Hello! I'm OpenClaw, your AI assistant. How can I help you today? 🦞"
            
            userMessage.contains("help", ignoreCase = true) ->
                "I can help you with various tasks! Just type your request and I'll do my best to assist. You can ask me to:\n" +
                "- Answer questions\n" +
                "- Help with analysis\n" +
                "- Generate content\n" +
                "- And much more!"
            
            userMessage.contains("status", ignoreCase = true) ->
                "System Status:\n" +
                "✓ Connected\n" +
                "✓ Database: OK\n" +
                "✓ Session: $sessionId\n" +
                "\nReady to help!"
            
            else -> 
                "Thanks for your message! This is a placeholder response.\n\n" +
                "To enable real OpenClaw integration, we need to:\n" +
                "1. Configure the API endpoint in Config.kt\n" +
                "2. Implement the WebSocket/REST API client\n" +
                "3. Connect to your OpenClaw Gateway\n\n" +
                "Your message: \"$userMessage\""
        }
    }
    
    /**
     * Clear error message
     */
    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
    
    /**
     * Clear all messages
     */
    fun clearHistory() {
        viewModelScope.launch {
            messageDao.deleteAll()
        }
    }
    
    // Extension functions to convert between domain and entity models
    private fun Message.toEntity(): MessageEntity {
        return MessageEntity(
            id = id,
            content = content,
            isFromUser = isFromUser,
            timestamp = timestamp,
            status = status.name,
            sessionId = metadata?.sessionId,
            tokens = metadata?.tokens,
            model = metadata?.model,
            duration = metadata?.duration,
            error = metadata?.error
        )
    }
    
    private fun MessageEntity.toDomainModel(): Message {
        return Message(
            id = id,
            content = content,
            isFromUser = isFromUser,
            timestamp = timestamp,
            status = MessageStatus.valueOf(status),
            metadata = if (sessionId != null) {
                com.openclaw.chat.domain.model.MessageMetadata(
                    sessionId = sessionId,
                    tokens = tokens,
                    model = model,
                    duration = duration,
                    error = error
                )
            } else null
        )
    }
}
