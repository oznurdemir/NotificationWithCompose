package com.example.notificationwithcompose

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

// Context üzerinden DataStore Preferences örneği oluştur
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "LocalStore")

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Fonksiyonu çağır
        createNotificationChannel()
    }

    // Bildirim Kanalı oluştur
    private fun createNotificationChannel() {
        val name = "JetpackPushNotification"
        val description = "Jetpack Push Notification"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        // Bildirim Kanalı oluştur(Bildirim kanalı,
        // Android 8.0 (API seviyesi 26) ve üstü sürümlerde bildirimlerin yönetimini sağlayan bir mekanizmadır.)
        // Üç parametre alır: kanal ID'si, ismi ve önemi.
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel("Global", name, importance).apply {
                this.description = description
            }
        } else {
            null // O sürümünden küçükse bir şey yapmamak için null döndür
        }

        // Bildirim Yöneticisini al
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Bildirim kanalını oluştur
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (channel != null) {
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}
