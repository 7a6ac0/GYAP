package me.tabacowang.giveyouapunch.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {
    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        val settings: FirebaseFirestoreSettings = FirebaseFirestoreSettings
                .Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build()

        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance().apply {
            firestoreSettings = settings
        }
        return firestore
    }
}