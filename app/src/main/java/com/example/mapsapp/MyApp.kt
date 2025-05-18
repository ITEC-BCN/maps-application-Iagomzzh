package com.example.mapsapp

import android.app.Application
import com.example.mapsapp.data.MySupabaseClient

class MyApp : Application() {
    companion object {
        lateinit var database: MySupabaseClient
    }

    override fun onCreate() {
        super.onCreate()
        database = MySupabaseClient(
            supabaseUrl = "https://nqcvfzxjrkqynmhykmvv.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im5xY3ZmenhqcmtxeW5taHlrbXZ2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDU4Mjc4NTUsImV4cCI6MjA2MTQwMzg1NX0.m0JobXeoahlMYAr2GyveDbta-blW_RLbFe44fsv3fsg"
        )
    }
}
