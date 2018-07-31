package me.tabacowang.giveyouapunch.repository

import android.arch.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore
import me.tabacowang.giveyouapunch.AppExecutors
import me.tabacowang.giveyouapunch.firestore.FirestoreResource
import me.tabacowang.giveyouapunch.firestore.asLiveData
import me.tabacowang.giveyouapunch.vo.Punch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PunchRepository
@Inject constructor(
        private val appExecutors: AppExecutors,
        private val firestore: FirebaseFirestore
) {
    fun loadPunches(): LiveData<FirestoreResource<List<Punch>>>? {
        return firestore.collection("Punches").asLiveData<Punch>()
    }

    fun loadPunch(punchId: String): LiveData<FirestoreResource<Punch>> {
        return firestore.collection("Punches").document(punchId).asLiveData<Punch>()
    }

    fun addPunch(punch: Punch) {
        firestore.collection("Punches").asLiveData<Punch>().add(punch)
    }

    fun updatePunch(punch: Punch) {
        firestore.collection("Punches").document(punch.id!!).asLiveData<Punch>().set(punch)
    }
}