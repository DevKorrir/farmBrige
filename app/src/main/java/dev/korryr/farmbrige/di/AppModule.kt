package dev.korryr.farmbrige.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.korryr.farmbrige.ui.features.auth.dataModel.AuthService
import dev.korryr.farmbrige.ui.features.auth.dataModel.FirebaseAuthImpl
import dev.korryr.farmbrige.ui.features.auth.preference.AuthPreferenceRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFireBaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthServiceImpl(
        firebaseAuth: FirebaseAuth,
    ): AuthService = FirebaseAuthImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideAuthPreferenceRepo(
        @ApplicationContext context: Context // inject application context
    ): AuthPreferenceRepo {
        return AuthPreferenceRepo(context) // pass context here to the constructor
    }

//    @Provides
//    @Singleton
//    fun provideFirebaseAuthen(): FirebaseAuth {
//        return FirebaseAuth.auth
//
//    }


}