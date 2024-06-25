package com.app.supabasechat.network
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

object SupabaseClient {
    /*val client = createSupabaseClient(
        supabaseUrl = "URL",
        supabaseKey = "Key"
    ) {
        install(Storage)
        install(Postgrest)
        install(Realtime)
    }*/
    private const val SUPABASE_URL = "https://<your-supabase-url>"
    private const val SUPABASE_KEY = "your-anon-key"

    val client = createSupabaseClient(SUPABASE_URL, SUPABASE_KEY){
        install(Realtime)
        install(Postgrest)
    }
}