package dev.korryr.farmbrige.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.korryr.farmbrige.ui.features.auth.dataModel.AuthService
import dev.korryr.farmbrige.ui.features.auth.dataModel.FirebaseAuthImpl
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

//    @Provides
//    @Singleton
//    fun provideFirebaseAuthen(): FirebaseAuth {
//        return FirebaseAuth.auth
//
//    }


}