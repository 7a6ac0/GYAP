package me.tabacowang.giveyouapunch.repository

import com.google.firebase.firestore.FirebaseFirestore
import me.tabacowang.giveyouapunch.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PunchRepository
@Inject constructor(
        private val appExecutors: AppExecutors,
        private val firestore: FirebaseFirestore) {

}