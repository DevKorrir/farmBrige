package dev.korryr.farmbrige.aplplication.mainApp

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FarmBrigeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize any libraries or components here

        FirebaseApp.initializeApp(this)

    }
}