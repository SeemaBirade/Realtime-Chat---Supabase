package com.app.supabasechat.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: String,
    val userId: String,
    val content: String,
    val createdAt: String
)