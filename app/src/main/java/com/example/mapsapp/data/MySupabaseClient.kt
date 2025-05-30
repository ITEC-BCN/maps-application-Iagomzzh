package com.example.mapsapp.data

import android.util.Log
import com.example.mapsapp.models.Marcador
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.exception.PostgrestRestException
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage


class MySupabaseClient() {
    lateinit var client: SupabaseClient
    lateinit var storage: Storage

    constructor(supabaseUrl: String, supabaseKey: String) : this() {
        client = createSupabaseClient(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey
        ) {
            install(Postgrest)
            install(Storage)
        }
        storage = client.storage


    }

    suspend fun getAllMarkers(): List<Marcador> {
        return try {
            client.postgrest.from("marcador").select().decodeList<Marcador>()
        } catch (e: PostgrestRestException) {
            Log.e("MySupabaseClient", "Error en getAllMarkers: ${e.message}. Verificar API key.")
            throw e
        }
    }

    suspend fun insertMarker(marcador: Marcador) {
        try {
            client.postgrest.from("marcador").insert(marcador)
        } catch (e: PostgrestRestException) {
            Log.e("MySupabaseClient", "Error en insertMarker: ${e.message}. Verificar API key.")
            throw e
        }
    }


}