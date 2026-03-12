package com.openclaw.chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
 * NO LOCAL DATABASE - All messages from OpenClaw API only
 */
@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    
    private val _state = MutableStateFlow(ConversationState())
    val state: StateFlow<ConversationState> = _state.asStateFlow()
    
    // Session ID for this conversation
    private val sessionId = UUID.randomUUID().toString()
    
    // Connection state
    private var isConnected = false
    
    // In-memory message list (not persisted)
    private val messageList = mutableListOf<Message>()
    
    init {
        // Connect to OpenClaw on init
        connectToOpenClaw()
    }
    
    /**
     * Connect to OpenClaw Gateway
     */
    private fun connectToOpenClaw() {
        viewModelScope.launch {
            // TODO: Implement WebSocket connection to OpenClaw
            // For now, simulate connection
            kotlinx.coroutines.delay(500)
            isConnected = true
            _state.value = _state.value.copy(isConnected = true)
            
            // Add welcome message
            addMessage(
                Message(
                    content = "Xin chào sếp! Em là OpenClaw assistant. Sếp cần em giúp gì ạ? 🦞",
                    isFromUser = false,
                    status = MessageStatus.DELIVERED
                )
            )
        }
    }
    
    /**
     * Send a message to OpenClaw
     */
    fun sendMessage(content: String) {
        if (!isConnected) {
            _state.value = _state.value.copy(
                error = "Không kết nối được với OpenClaw. Sếp kiểm tra lại Config.kt nhé!"
            )
            return
        }
        
        viewModelScope.launch {
            // Create user message
            val userMessage = Message(
                content = content,
                isFromUser = true,
                status = MessageStatus.SENT
            )
            
            // Add to UI immediately (optimistic update)
            addMessage(userMessage)
            
            // Update state to loading
            _state.value = _state.value.copy(
                isLoading = true,
                error = null
            )
            
            try {
                // TODO: Send to OpenClaw API via WebSocket/REST
                // For now, simulate response
                simulateOpenClawResponse(content)
            } catch (e: Exception) {
                // Update message status to failed
                val failedMessage = userMessage.copy(
                    status = MessageStatus.FAILED,
                    metadata = userMessage.metadata?.copy(error = e.message)
                )
                updateMessage(failedMessage)
                
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Gửi lỗi rồi sếp: ${e.message}"
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
        
        // Add to UI
        addMessage(assistantMessage)
        
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
            userMessage.contains("hi", ignoreCase = true) ||
            userMessage.contains("chào", ignoreCase = true) -> 
                "Dạ em chào sếp! Em sẵn sàng hỗ trợ sếp ạ! 🦞\n\nSếp cần em giúp gì không ạ?"
            
            userMessage.contains("help", ignoreCase = true) ||
            userMessage.contains("giúp", ignoreCase = true) ->
                "Dạ em có thể giúp sếp với:\n" +
                "• Trả lời câu hỏi\n" +
                "• Phân tích dữ liệu\n" +
                "• Tạo nội dung\n" +
                "• Automation tasks\n" +
                "• Và nhiều thứ khác nữa ạ!\n\n" +
                "Sếp cứ việc giao việc cho em nhé! 💪"
            
            userMessage.contains("status", ignoreCase = true) ||
            userMessage.contains("trạng thái", ignoreCase = true) ->
                "📊 System Status:\n" +
                "✓ Connected to OpenClaw\n" +
                "✓ Session: ${sessionId.take(8)}...\n" +
                "✓ Messages: ${messageList.size}\n" +
                "✓ Uptime: All day\n\n" +
                "Em sẵn sàng nhận lệnh từ sếp! 🫡"
            
            userMessage.contains("app", ignoreCase = true) ||
            userMessage.contains("android", ignoreCase = true) ->
                "Dạ app Android em đã được tạo xong rồi ạ!\n\n" +
                "📱 Features:\n" +
                "• Chat interface như Telegram\n" +
                "• Material Design 3\n" +
                "• Real-time messaging\n" +
                "• Không lưu memory (như sếp yêu cầu)\n\n" +
                "Sếp chỉ cần mở Android Studio và build thôi ạ! 🚀"
            
            else -> 
                "Dạ em nhận được tin nhắn của sếp rồi ạ! 🦞\n\n" +
                "Tin nhắn: \"${userMessage}\"\n\n" +
                "Để em phản hồi chính xác, sếp cần cấu hình OpenClaw API trong Config.kt:\n" +
                "1. Update OPENCLAW_HOST (IP PC của sếp)\n" +
                "2. Update API_TOKEN (từ Control UI)\n" +
                "3. Build lại app\n\n" +
                "Em sẽ connect thật với OpenClaw Gateway ngay ạ! 💪"
        }
    }
    
    /**
     * Add message to list and update state
     */
    private fun addMessage(message: Message) {
        messageList.add(message)
        _state.value = _state.value.copy(
            messages = messageList.toList()
        )
    }
    
    /**
     * Update existing message (e.g., status change)
     */
    private fun updateMessage(updatedMessage: Message) {
        val index = messageList.indexOfFirst { it.id == updatedMessage.id }
        if (index >= 0) {
            messageList[index] = updatedMessage
            _state.value = _state.value.copy(
                messages = messageList.toList()
            )
        }
    }
    
    /**
     * Clear error message
     */
    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
    
    /**
     * Clear all messages (in-memory only)
     */
    fun clearHistory() {
        messageList.clear()
        _state.value = _state.value.copy(
            messages = emptyList()
        )
    }
    
    override fun onCleared() {
        super.onCleared()
        // No database to close, no cleanup needed
        // Messages will be lost when app closes (by design)
    }
}
