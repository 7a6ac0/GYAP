package me.tabacowang.giveyouapunch.vo

import com.google.firebase.Timestamp
import me.tabacowang.giveyouapunch.firestore.FirestoreModel

data class Punch(
        val title: String? = null,
        val content: String? = null,
        val count: Int = 0,
        val created_at: Timestamp? = null
) : FirestoreModel() {
}