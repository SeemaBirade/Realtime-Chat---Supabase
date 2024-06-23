package com.app.supabasechat.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.supabasechat.viewmodel.repository.ChatRepository
import com.app.supabasechat.R
import com.app.supabasechat.view.adapter.MessageAdapter
import com.app.supabasechat.databinding.ActivityChatBinding
import com.app.supabasechat.model.Message
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.broadcastFlow
import io.github.jan.supabase.realtime.createChannel
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var chatRepository: ChatRepository

    val supabaseNew = createSupabaseClient(
        supabaseUrl = "https://yhidwffcapydaikqgmuy.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InloaWR3ZmZjYXB5ZGFpa3FnbXV5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTkwMzk1NjgsImV4cCI6MjAzNDYxNTU2OH0._z5UyintuVDjEC6ioWQMyYbQc_Agk8bJghgQJU5PHKE"
    ) {
        install(Postgrest)
        install(Realtime)
        install(GoTrue)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageAdapter = MessageAdapter()
        binding.recyclerViewMessages.apply {
            adapter = messageAdapter
        }
        chatRepository = ChatRepository()
        // Check for current logged user
        val user = supabaseNew.gotrue.currentUserOrNull()
        if (user == null) {
            Toast.makeText(this@ChatActivity, "User Not found!", Toast.LENGTH_SHORT).show()
            finish() // User is not logged in
            return
        }

        // Set send button click listener
        binding.buttonSend.setOnClickListener {
            val content = binding.editTextMessage.text.toString()
            if (content.isNotEmpty()) {
                lifecycleScope.launch {
                    try {
                        chatRepository.sendMessage(content, user.id)
                        binding.editTextMessage.setText("")
                    } catch (e: Exception) {
                        Log.e("ChatActivity", "Error sending message", e)
                    }
                }
            }
        }
        lifecycleScope.launch {
            loadMessages()
        }
    }

    private suspend fun loadMessages() {
        val messages = chatRepository.getMessages()
        messageAdapter.setMessages(messages)
    }
}