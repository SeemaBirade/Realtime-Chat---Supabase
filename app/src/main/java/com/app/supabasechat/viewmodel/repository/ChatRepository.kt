package com.app.supabasechat.viewmodel.repository

import com.app.supabasechat.model.Message
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class ChatRepository {

    val supabaseClient = createSupabaseClient(
        supabaseUrl = "https://yhidwffcapydaikqgmuy.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InloaWR3ZmZjYXB5ZGFpa3FnbXV5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTkwMzk1NjgsImV4cCI6MjAzNDYxNTU2OH0._z5UyintuVDjEC6ioWQMyYbQc_Agk8bJghgQJU5PHKE"
    ){

    }

    /**
     * Send a message to the Supabase database
     */
    suspend fun sendMessage(content: String, userId: String) {
        val data = mapOf("content" to content, "user_id" to userId)
        supabaseClient.postgrest.from("messages").insert(data)
    }

    /**
     * Get a messages from the Supabase database
     */
    suspend fun getMessages(): List<Message> {
        val response = supabaseClient.postgrest.from("messages").select(Columns.list("*")).decodeList<Message>()
        return response
    }

    /**
     * Subscribe to new messages in the Supabase database
     */
    fun subscribeToMessages(onMessage: (Message) -> Unit) {

        /*supabaseClient.postgrest.from("messages").o(Columns.list("*")).decodeList<Message>()

        supabaseClient.from("messages").on("INSERT") { payload ->
            val message = payload.newRecord.toObject(Message::class.java)
            onMessage(message)
        }.subscribe()*/
    }
}