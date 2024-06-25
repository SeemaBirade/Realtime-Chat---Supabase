package com.app.supabasechat.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.supabasechat.databinding.ActivityRegistrationBinding
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    val supabaseClient = createSupabaseClient(
        supabaseUrl = "https://yhidwffcapydaikqgmuy.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InloaWR3ZmZjYXB5ZGFpa3FnbXV5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTkwMzk1NjgsImV4cCI6MjAzNDYxNTU2OH0._z5UyintuVDjEC6ioWQMyYbQc_Agk8bJghgQJU5PHKE"
    ) {
        install(Postgrest)
        install(Realtime)
        install(GoTrue)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set register button click listener
        binding.buttonSignUp.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            lifecycleScope.launch {
                if (signUp(email, password)) {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Sign up successful, please log in",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(this@RegistrationActivity, "Sign up failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    /**
     * Register user using Supabase GoTrue
     */
    suspend fun signUp(email: String, password: String): Boolean {
        try {
            val result = supabaseClient.gotrue.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            return true
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                handleException(e)
            }
            return false
        }
    }

    /**
     * Handle exceptions and display appropriate messages
     */
    private fun handleException(exception: Exception) {
        when {
            exception.message?.contains("Email rate limit exceeded") == true -> {
                Toast.makeText(this, "Rate limit exceeded. Please try again later.", Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(this, "An error occurred: ${exception.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}